package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.*

data class DestinationReview(
    val author: String,
    val rating: Int,
    val comment: String,
    val date: String
)

val defaultReviews = mapOf(
    "halong" to listOf(
        DestinationReview("Nguyễn Hoàng", 5, "Cảnh quan thiên nhiên thật hùng vĩ, trải nghiệm đi du thuyền cực kỳ đáng nhớ!", "20/05/2026"),
        DestinationReview("Minh Anh", 5, "Vịnh tuyệt đẹp, món chả mực giã tay rất ngon và lôi cuốn.", "18/05/2026")
    ),
    "hoian" to listOf(
        DestinationReview("Trần Thanh", 5, "Phố cổ Hội An về đêm lung linh ánh đèn lồng, đồ ăn ngon rẻ vô cùng!", "24/05/2026"),
        DestinationReview("Lê Nam", 4, "Nước Mót ngon thanh mát, tuy nhiên phố cổ cuối tuần đi bộ hơi đông đúc.", "22/05/2026")
    ),
    "sapa" to listOf(
        DestinationReview("Phạm Đạt", 5, "Trời lạnh sương mù đẹp ảo diệu. Cáp treo Fansipan rất đáng để đi một lần trong đời.", "21/05/2026"),
        DestinationReview("Hương Ly", 4, "Bản Cát Cát xinh lung linh, ẩm thực đồ nướng Sa Pa phong phú và ấm bụng.", "15/05/2026")
    ),
    "muine" to listOf(
        DestinationReview("Quốc Việt", 5, "Đồi cát đỏ hoàng hôn lãng mạn vô bờ bến. Đi xe jeep trải nghiệm ngắm cảnh thật say mê.", "12/05/2026")
    ),
    "phuquoc" to listOf(
        DestinationReview("Thùy Chi", 5, "Bãi Sao nước trong vắt xanh ngọc bích. Buffet hoàng hôn lãng mạn vô cùng thích thú.", "23/05/2026"),
        DestinationReview("Đại Nghĩa", 4, "VinWonders vui nhộn, bún quậy ăn siêu lạ miệng nhưng ngon, đáng thử.", "19/05/2026")
    ),
    "dalat" to listOf(
        DestinationReview("Thanh Thảo", 5, "Không khí se lạnh dễ chịu mát lạnh. Các quán cà phê ngắm đồi thông rất xinh xắn yên bình.", "22/05/2026")
    ),
    "hue" to listOf(
        DestinationReview("Gia Bảo", 5, "Đại Nội trầm mặc uy nghiêm, bún bò Huế thơm cay nồng ấm.", "14/05/2026"),
        DestinationReview("Bảo Khánh", 4, "Ca Huế trên sông Hương rất tuyệt vời, nhiều kiến trúc cổ kính cổ xưa.", "10/05/2026")
    ),
    "ninhbinh" to listOf(
        DestinationReview("Văn Tiến", 5, "Cơm cháy ngon giòn tan rôm rả, ngồi thuyền Tràng An thật thanh thản lôi cuốn lý thú.", "16/05/2026")
    ),
    "cantho" to listOf(
        DestinationReview("Tú Uyên", 5, "Chợ nổi Cái Răng tấp nập sầm uất hào hứng, trái cây miệt vườn tươi ngọt bùi béo.", "08/05/2026")
    ),
    "condao" to listOf(
        DestinationReview("Hoàng Long", 5, "Thiên đường hoang sơ trong trẻo bậc nhất Việt Nam, bờ biển cát trắng mướt mải bình yên.", "24/05/2026")
    )
)

fun saveReviewsToPrefs(context: android.content.Context, destId: String, reviews: List<DestinationReview>) {
    val prefs = context.getSharedPreferences("vietnam_travel_reviews", android.content.Context.MODE_PRIVATE)
    val serialized = reviews.joinToString("###") { "${it.author}|||${it.rating}|||${it.comment}|||${it.date}" }
    prefs.edit().putString(destId, serialized).apply()
}

fun loadReviewsFromPrefs(context: android.content.Context, destId: String, defaultList: List<DestinationReview>): List<DestinationReview> {
    val prefs = context.getSharedPreferences("vietnam_travel_reviews", android.content.Context.MODE_PRIVATE)
    val serialized = prefs.getString(destId, null) ?: return defaultList
    if (serialized.isEmpty()) return emptyList()
    return try {
        serialized.split("###").map { item ->
            val parts = item.split("|||")
            DestinationReview(
                author = parts[0],
                rating = parts[1].toInt(),
                comment = parts[2],
                date = parts[3]
            )
        }
    } catch (e: Exception) {
        defaultList
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets.safeDrawing
                ) { innerPadding ->
                    TravelAppMain(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TravelAppMain(modifier: Modifier = Modifier) {
    val context = androidx.compose.ui.platform.LocalContext.current
    
    // Reviews state loading first from prefs, fallback to default
    var reviewsState by remember {
        mutableStateOf(
            sampleDestinations.associate { dest ->
                val defaults = defaultReviews[dest.id] ?: emptyList()
                dest.id to loadReviewsFromPrefs(context, dest.id, defaults)
            }
        )
    }

    // Tab state: 0 for Exploration (Khám Phá), 1 for Saved (Đã lưu)
    var selectedTab by remember { mutableStateOf(0) }
    
    // Filtering states
    var searchQuery by remember { mutableStateOf("") }
    var selectedRegion by remember { mutableStateOf<Region?>(null) }
    var selectedType by remember { mutableStateOf<TravelType?>(null) }
    
    // Saved favorites list (using state of ids)
    var favorites by remember { mutableStateOf(setOf<String>()) }
    
    // Details popup state
    var selectedDestination by remember { mutableStateOf<Destination?>(null) }

    // Derived memoized list based on search/filters
    val filteredDestinations = remember(searchQuery, selectedRegion, selectedType, favorites, selectedTab) {
        sampleDestinations.filter { dest ->
            // Tab filter
            val matchesTab = if (selectedTab == 1) favorites.contains(dest.id) else true
            
            // Search filter (handles accentless check roughly, but search matches fully lowercase)
            val query = searchQuery.trim().lowercase()
            val matchesSearch = query.isEmpty() || 
                    dest.nameVn.lowercase().contains(query) || 
                    dest.nameEn.lowercase().contains(query) || 
                    dest.province.lowercase().contains(query)
            
            // Region filter
            val matchesRegion = selectedRegion == null || dest.region == selectedRegion
            
            // Type filter
            val matchesType = selectedType == null || dest.type == selectedType

            matchesTab && matchesSearch && matchesRegion && matchesType
        }
    }

    // Main design container
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Editorial Styled App Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Editorial tracking top banner label
                Text(
                    text = "VIET NAM TRAVEL GUIDE",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = RedFlag,
                    letterSpacing = 2.sp
                )

                // Compact indicator of favorites total in the corner
                if (favorites.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(RedFlag.copy(alpha = 0.1f))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Favorites Count",
                                tint = RedFlag,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${favorites.size}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = RedFlag
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Khám Phá",
                        fontFamily = FontFamily.Serif,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onBackground,
                        lineHeight = 36.sp
                    )
                    Text(
                        text = "Việt Nam",
                        fontFamily = FontFamily.Serif,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Black,
                        color = RedFlag,
                        lineHeight = 36.sp,
                        style = androidx.compose.ui.text.TextStyle(
                            fontStyle = FontStyle.Italic
                        )
                    )
                }

                // Decorative warm gold flag-motif star
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .shadow(3.dp, shape = CircleShape)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface)
                        .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Ngôi sao vàng Việt Nam",
                        tint = GoldWarm,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        // Horizontal Segment Tabs: Khám Phá & Đã Lưu
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f))
                .padding(4.dp)
        ) {
            TabButton(
                title = "Khám Phá",
                icon = Icons.Default.Explore,
                selected = selectedTab == 0,
                modifier = Modifier
                    .weight(1f)
                    .testTag("explore_tab")
            ) {
                selectedTab = 0
            }
            
            TabButton(
                title = "Đã lưu (${favorites.size})",
                icon = Icons.Default.Favorite,
                selected = selectedTab == 1,
                modifier = Modifier
                    .weight(1f)
                    .testTag("saved_tab")
            ) {
                selectedTab = 1
            }
        }

        // Quick list container
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            // Optional Hero Section shown in "Khám phá" with overall stats
            if (selectedTab == 0 && searchQuery.isEmpty() && selectedRegion == null && selectedType == null) {
                item {
                    HeroSection()
                }
            }

            // Search and Filters Sticky-looking Section in header
            item {
                SearchAndFiltersBlock(
                    searchQuery = searchQuery,
                    onSearchChange = { searchQuery = it },
                    selectedRegion = selectedRegion,
                    onRegionSelect = { selectedRegion = if (selectedRegion == it) null else it },
                    selectedType = selectedType,
                    onTypeSelect = { selectedType = if (selectedType == it) null else it }
                )
            }

            // Destination list headers / count indicators
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (selectedTab == 1) "Địa điểm đã yêu thích" else "Địa điểm dành cho bạn",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    
                    Text(
                        text = "Tìm thấy ${filteredDestinations.size} địa điểm",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Empty state helper
            if (filteredDestinations.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(48.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = if (selectedTab == 1) Icons.Default.FavoriteBorder else Icons.Default.SearchOff,
                            contentDescription = "No items",
                            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = if (selectedTab == 1) "Danh sách yêu thích trống.\nHãy lưu lại những địa điểm bạn thích!" else "Không tìm thấy địa điểm phù hợp.\nThử thay đổi bộ lọc hoặc từ khóa tìm kiếm xem sao!",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                        
                        if (searchQuery.isNotEmpty() || selectedRegion != null || selectedType != null) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = {
                                    searchQuery = ""
                                    selectedRegion = null
                                    selectedType = null
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = RedFlag
                                )
                            ) {
                                Text("Xóa tìm kiếm & bộ lọc")
                            }
                        }
                    }
                }
            } else {
                // Adaptive single columns or customized lists
                items(filteredDestinations) { destination ->
                    val reviews = reviewsState[destination.id] ?: emptyList()
                    val averageRating = if (reviews.isEmpty()) destination.rating else reviews.map { it.rating }.average()
                    DestinationCardItem(
                        destination = destination,
                        isFavorite = favorites.contains(destination.id),
                        onFavoriteToggle = {
                            favorites = if (favorites.contains(destination.id)) {
                                favorites - destination.id
                            } else {
                                favorites + destination.id
                            }
                        },
                        onViewDetails = {
                            selectedDestination = destination
                        },
                        customRating = averageRating
                    )
                }
            }
        }
    }

    // Modal view for travel item details
    selectedDestination?.let { dest ->
        val reviews = reviewsState[dest.id] ?: emptyList()
        DestinationDetailDialog(
            destination = dest,
            isFavorite = favorites.contains(dest.id),
            onFavoriteToggle = {
                favorites = if (favorites.contains(dest.id)) {
                    favorites - dest.id
                } else {
                    favorites + dest.id
                }
            },
            onDismiss = { selectedDestination = null },
            reviews = reviews,
            onAddReview = { newReview ->
                val currentList = reviewsState[dest.id] ?: emptyList()
                val updatedList = currentList + newReview
                saveReviewsToPrefs(context, dest.id, updatedList)
                reviewsState = reviewsState + (dest.id to updatedList)
            }
        )
    }
}

@Composable
fun TabButton(
    title: String,
    icon: ImageVector,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val containerBg = if (selected) MaterialTheme.colorScheme.surface else Color.Transparent
    val shadowElevation = if (selected) 2.dp else 0.dp
    val textAndIconColor = if (selected) RedFlag else MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = modifier
            .padding(2.dp)
            .shadow(shadowElevation, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(containerBg)
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp, horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = textAndIconColor,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                color = textAndIconColor
            )
        }
    }
}

@Composable
fun HeroSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(26.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        RedFlag,
                        GoldWarm,
                        SeaBlue
                    )
                )
            )
            .padding(22.dp)
    ) {
        Column {
            Text(
                text = "Cùng du ngoạn khắp",
                fontSize = 13.sp,
                color = Color.White.copy(alpha = 0.85f),
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Kỳ Quan Đất Việt",
                fontFamily = FontFamily.Serif,
                fontSize = 28.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                style = androidx.compose.ui.text.TextStyle(
                    fontStyle = FontStyle.Italic
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Hành trình mây ngàn, đảo ngọc hữu tình, sông nước mộc mạc và ẩm thực đậm sắc màu lịch sử truyền thống.",
                fontSize = 13.sp,
                color = Color.White.copy(alpha = 0.9f),
                lineHeight = 18.sp
            )
            
            Spacer(modifier = Modifier.height(18.dp))
            
            // Stats indicator grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatBadge(value = "12", label = "Điểm ví dặm", modifier = Modifier.weight(1f))
                StatBadge(value = "10+", label = "Tỉnh / Thành", modifier = Modifier.weight(1f))
                StatBadge(value = "7", label = "Loại hình", modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun StatBadge(value: String, label: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = 0.2f))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = label,
                fontSize = 10.sp,
                color = Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SearchAndFiltersBlock(
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    selectedRegion: Region?,
    onRegionSelect: (Region) -> Unit,
    selectedType: TravelType?,
    onTypeSelect: (TravelType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            placeholder = { Text("Tìm địa điểm, tỉnh thành... (Vịnh Hạ Long, Hội An,...)") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "SearchIcon",
                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                )
            },
            trailingIcon = if (searchQuery.isNotEmpty()) {
                {
                    IconButton(onClick = { onSearchChange("") }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear Search",
                            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                        )
                    }
                }
            } else null,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("search_input"),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = RedFlag,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Region quick filter
        Text(
            text = "Khu vực / Miền:",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
        Spacer(modifier = Modifier.height(6.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(Region.values()) { region ->
                val isSelected = selectedRegion == region
                FilterChipItem(
                    text = region.vnName,
                    selected = isSelected,
                    modifier = Modifier.testTag("region_filter_${region.name}")
                ) {
                    onRegionSelect(region)
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Tourism Type Filter
        Text(
            text = "Loại hình du lịch:",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
        Spacer(modifier = Modifier.height(6.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(TravelType.values()) { type ->
                val isSelected = selectedType == type
                FilterChipItem(
                    text = type.vnName,
                    selected = isSelected,
                    modifier = Modifier.testTag("type_filter_${type.name}")
                ) {
                    onTypeSelect(type)
                }
            }
        }
    }
}

@Composable
fun FilterChipItem(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val bg = if (selected) RedFlag else MaterialTheme.colorScheme.surface
    val borderCol = if (selected) RedFlag else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
    val textColor = if (selected) Color.White else MaterialTheme.colorScheme.onSurface

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(bg)
            .border(1.dp, borderCol, RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
            color = textColor
        )
    }
}

@Composable
fun DestinationCardItem(
    destination: Destination,
    isFavorite: Boolean,
    onFavoriteToggle: () -> Unit,
    onViewDetails: () -> Unit,
    customRating: Double = destination.rating
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("card_${destination.id}")
            .clickable(onClick = onViewDetails)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f),
                shape = RoundedCornerShape(26.dp)
            ),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            // Gradient Header placeholder mirroring images brilliantly
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = destination.gradientColors
                        )
                    )
            ) {
                // Background artistic geometric watermarks/sun
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.45f))
                            )
                        )
                )

                // Ratings on top right
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(14.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Black.copy(alpha = 0.6f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Rating Star",
                            tint = GoldWarm,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "%.1f".format(customRating),
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Regional Tag top left
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(14.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(RedFlag)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = destination.region.vnName,
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Travel Type tag bottom left
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(14.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.25f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = destination.type.vnName,
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Body
            Column(modifier = Modifier.padding(16.dp)) {
                // Title
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = destination.nameVn,
                            fontFamily = FontFamily.Serif,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = destination.nameEn,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            fontWeight = FontWeight.Medium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    
                    // Favorite heartbeat
                    IconButton(
                        onClick = onFavoriteToggle,
                        modifier = Modifier.testTag("favorite_button_${destination.id}")
                    ) {
                        val favScale by animateFloatAsState(if (isFavorite) 1.2f else 1.0f)
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Save Favorite",
                            tint = if (isFavorite) RedFlag else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.35f),
                            modifier = Modifier
                                .size(26.dp)
                                .scale(favScale)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Location tag
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = "Place Symbol",
                        tint = JungleGreen,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = destination.province,
                        fontSize = 13.sp,
                        color = JungleGreen,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Description
                Text(
                    text = destination.shortDesc,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // View detail button
                Button(
                    onClick = onViewDetails,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .testTag("view_details_button_${destination.id}"),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RedFlag
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Xem chi tiết",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Detail Icon",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DestinationDetailDialog(
    destination: Destination,
    isFavorite: Boolean,
    onFavoriteToggle: () -> Unit,
    onDismiss: () -> Unit,
    reviews: List<DestinationReview>,
    onAddReview: (DestinationReview) -> Unit
) {
    val averageRating = if (reviews.isEmpty()) destination.rating else reviews.map { it.rating }.average()
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false // Fills responsive boundaries on tablets/phones
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .systemBarsPadding(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // Header Banner mimicking image using grand gradient background
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .background(
                            brush = Brush.linearGradient(
                                colors = destination.gradientColors
                            )
                        )
                ) {
                    // Dark fade overlay
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Black.copy(alpha = 0.2f),
                                        Color.Black.copy(alpha = 0.7f)
                                    )
                                )
                            )
                    )

                    // Top Action bar over the hero space
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Close Circle button
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.Black.copy(alpha = 0.5f))
                                .clickable(onClick = onDismiss)
                                .testTag("close_details_button"),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back Button",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        // Save heart state selector inside modal
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.Black.copy(alpha = 0.5f))
                                .clickable(onClick = onFavoriteToggle)
                                .testTag("save_favorite_button"),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "Save favorite",
                                tint = if (isFavorite) RedFlag else Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    // Lower tags on the cover image
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(20.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(RedFlag)
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = destination.region.vnName,
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.White.copy(alpha = 0.25f))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = destination.type.vnName,
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(8.dp))

                        // Destination master bilingual names
                        Text(
                            text = destination.nameVn,
                            fontFamily = FontFamily.Serif,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                        Text(
                            text = destination.nameEn,
                            fontSize = 14.sp,
                            color = Color.White.copy(alpha = 0.82f),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // Profile Detailed Body contents
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    // Quick stats list
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        RatingIndicatorStat(rating = averageRating)
                        DividerVertical()
                        SimpleStatCell(label = "Tỉnh thành", value = destination.province)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Full description
                    Text(
                        text = "Giới thiệu địa danh",
                        fontFamily = FontFamily.Serif,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = destination.longDesc,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Tourist basic operational details
                    Text(
                        text = "Thông tin hữu ích",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    
                    InfoRowItem(
                        icon = Icons.Default.Place,
                        label = "Địa chỉ",
                        value = destination.address,
                        iconColor = JungleGreen
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    InfoRowItem(
                        icon = Icons.Default.CalendarToday,
                        label = "Thời điểm lý tưởng nhất",
                        value = destination.bestTime,
                        iconColor = GoldWarm
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    InfoRowItem(
                        icon = Icons.Default.ConfirmationNumber,
                        label = "Vé tham quan",
                        value = destination.ticketPrice,
                        iconColor = RedFlag
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Highlights bullet points
                    Text(
                        text = "Điểm nổi bật không thể bỏ lỡ ✨",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    destination.highlights.forEach { h ->
                        BulletListItem(text = h)
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Food recommendation section
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = GoldLight.copy(alpha = 0.4f)
                        ),
                        border = BorderStroke(1.dp, GoldWarm.copy(alpha = 0.3f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Restaurant,
                                    contentDescription = "Food icon",
                                    tint = GoldWarm,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Gợi ý ẩm thực bản địa 🍜",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = RedDeep
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            destination.foodieAdvice.forEach { food ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Circle,
                                        contentDescription = "Dot",
                                        tint = RedFlag,
                                        modifier = Modifier.size(6.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = food,
                                        fontSize = 13.sp,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Reviews & Comments section
                    Text(
                        text = "Đánh giá & Bình luận 💬",
                        fontFamily = FontFamily.Serif,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Overall summary of ratings
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f)
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "%.1f".format(averageRating),
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Black,
                                    color = RedFlag
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Row {
                                    for (i in 1..5) {
                                        Icon(
                                            imageVector = if (i <= averageRating) Icons.Filled.Star else Icons.Outlined.Star,
                                            contentDescription = null,
                                            tint = GoldWarm,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "(${reviews.size} đánh giá)",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                )
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            DividerVertical()
                            Spacer(modifier = Modifier.width(20.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Du khách nói gì về ${destination.nameVn}? Hãy cùng nhau đóng góp ý kiến để giúp chuyến đi của mọi người thêm phần trọn vẹn lý thú.",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                                    lineHeight = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Form to add review
                    var userRating by remember { mutableStateOf(5) }
                    var userName by remember { mutableStateOf("") }
                    var userComment by remember { mutableStateOf("") }
                    var inputError by remember { mutableStateOf<String?>(null) }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Viết đánh giá của bạn",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Chọn số sao:",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Row {
                                    for (i in 1..5) {
                                        IconButton(
                                            onClick = { userRating = i },
                                            modifier = Modifier
                                                .size(34.dp)
                                                .testTag("star_button_$i")
                                        ) {
                                            Icon(
                                                imageVector = if (i <= userRating) Icons.Filled.Star else Icons.Outlined.Star,
                                                contentDescription = "Rating $i",
                                                tint = if (i <= userRating) GoldWarm else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                                                modifier = Modifier.size(24.dp)
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))

                            OutlinedTextField(
                                value = userName,
                                onValueChange = { 
                                    userName = it
                                    if (it.isNotBlank()) inputError = null
                                },
                                label = { Text("Tên của bạn") },
                                placeholder = { Text("Nhập tên của bạn...") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("input_author_name"),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = RedFlag,
                                    focusedLabelColor = RedFlag
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))

                            OutlinedTextField(
                                value = userComment,
                                onValueChange = { 
                                    userComment = it
                                    if (it.isNotBlank()) inputError = null
                                },
                                label = { Text("Ý kiến đánh giá") },
                                placeholder = { Text("Bình luận ngắn cảm nghĩ của bạn...") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("input_review_comment"),
                                minLines = 2,
                                maxLines = 4,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = RedFlag,
                                    focusedLabelColor = RedFlag
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )
                            
                            if (inputError != null) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = inputError!!,
                                    color = RedFlag,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Button(
                                onClick = {
                                    if (userName.trim().isEmpty()) {
                                        inputError = "Vui lòng nhập tên của bạn"
                                    } else if (userComment.trim().isEmpty()) {
                                        inputError = "Vui lòng nhập nội dung nhận xét"
                                    } else {
                                        val currentFormattedDate = try {
                                            val sdf = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
                                            sdf.format(java.util.Date())
                                        } catch (e: Exception) {
                                            "24/05/2026"
                                        }
                                        
                                        val newReview = DestinationReview(
                                            author = userName.trim(),
                                            rating = userRating,
                                            comment = userComment.trim(),
                                            date = currentFormattedDate
                                        )
                                        onAddReview(newReview)
                                        
                                        userName = ""
                                        userComment = ""
                                        userRating = 5
                                        inputError = null
                                    }
                                },
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .testTag("submit_review_button"),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = RedFlag
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = "Gửi đánh giá",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Review list items
                    if (reviews.isEmpty()) {
                        Text(
                            text = "Chưa có đánh giá nào cho địa điểm này. Hãy để lại một vài lời ý kiến của bạn!",
                            fontSize = 13.sp,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    } else {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            reviews.reversed().forEach { rev ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f)
                                    ),
                                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f))
                                ) {
                                    Row(
                                        modifier = Modifier.padding(12.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        val firstLetter = rev.author.trim().take(1).uppercase()
                                        Box(
                                            modifier = Modifier
                                                .size(36.dp)
                                                .clip(CircleShape)
                                                .background(RedFlag.copy(alpha = 0.1f)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = firstLetter,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 15.sp,
                                                color = RedFlag
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    text = rev.author,
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = MaterialTheme.colorScheme.onSurface
                                                )
                                                Text(
                                                    text = rev.date,
                                                    fontSize = 11.sp,
                                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                                                    fontWeight = FontWeight.Medium
                                                )
                                            }
                                            Spacer(modifier = Modifier.height(4.dp))
                                            
                                            Row {
                                                for (i in 1..5) {
                                                    Icon(
                                                        imageVector = Icons.Filled.Star,
                                                        contentDescription = null,
                                                        tint = if (i <= rev.rating) GoldWarm else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.15f),
                                                        modifier = Modifier.size(12.dp)
                                                    )
                                                }
                                            }
                                            Spacer(modifier = Modifier.height(6.dp))
                                            
                                            Text(
                                                text = rev.comment,
                                                fontSize = 13.sp,
                                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                                                lineHeight = 18.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Bottom Dismiss button inside modal sheet
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = RedFlag
                        )
                    ) {
                        Text(
                            text = "Đóng chi tiết",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color.White
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun DividerVertical() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(28.dp)
            .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f))
    )
}

@Composable
fun RatingIndicatorStat(rating: Double) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Score",
                tint = GoldWarm,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "$rating",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Text(
            text = "Đánh giá",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
fun SimpleStatCell(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
fun InfoRowItem(
    icon: ImageVector,
    label: String,
    value: String,
    iconColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .padding(top = 2.dp)
                .size(24.dp)
                .clip(CircleShape)
                .background(iconColor.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(14.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
            Text(
                text = value,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun BulletListItem(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "Bullet List Indicator",
            tint = JungleGreen,
            modifier = Modifier
                .padding(top = 3.dp)
                .size(14.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.85f),
            lineHeight = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
