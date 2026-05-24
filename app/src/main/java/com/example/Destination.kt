package com.example

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.RedFlag
import com.example.ui.theme.GoldWarm
import com.example.ui.theme.JungleGreen
import com.example.ui.theme.SeaBlue
import com.example.ui.theme.SeaBlueDark
import com.example.ui.theme.RedDeep

enum class Region(val vnName: String) {
    NORTH("Miền Bắc"),
    CENTRAL("Miền Trung"),
    SOUTH("Miền Nam")
}

enum class TravelType(val vnName: String) {
    HERITAGE("Di sản"),
    ANCIENT_TOWN("Phố cổ"),
    MOUNTAIN("Núi"),
    BEACH("Biển"),
    NATURE("Thiên nhiên"),
    FOOD("Ẩm thực"),
    HISTORY("Lịch sử")
}

data class Destination(
    val id: String,
    val nameVn: String,
    val nameEn: String,
    val province: String,
    val region: Region,
    val type: TravelType,
    val rating: Double,
    val shortDesc: String,
    val longDesc: String,
    val address: String,
    val bestTime: String,
    val ticketPrice: String,
    val highlights: List<String>,
    val foodieAdvice: List<String>,
    val gradientColors: List<Color>
)

val sampleDestinations = listOf(
    Destination(
        id = "halong",
        nameVn = "Vịnh Hạ Long",
        nameEn = "Ha Long Bay",
        province = "Quảng Ninh",
        region = Region.NORTH,
        type = TravelType.HERITAGE,
        rating = 5.0,
        shortDesc = "Một trong bảy kỳ quan thiên nhiên mới của thế giới với hàng ngàn hòn đảo kỳ vĩ nâng đỡ giữa nước biển xanh ngọc bích.",
        longDesc = "Vịnh Hạ Long được UNESCO nhiều lần công nhận là Di sản Thiên nhiên Thế giới với hàng nghìn hòn đảo đá vôi kỳ vĩ được tạo hóa đẽo gọt một cách tinh xảo. Du khách đến đây sẽ được đắm mình vào cảnh sắc trữ tình của mây trời, sóng nước, hang động kì bí và trải nghiệm chất lượng dịch vụ du thuyền đạt đẳng cấp quốc tế.",
        address = "Thành phố Hạ Long, Tỉnh Quảng Ninh, Việt Nam",
        bestTime = "Tháng 10 đến tháng 4 sang năm (Khí hậu dịu mát, trong lành)",
        ticketPrice = "290.000 VNĐ / vé tham quan chung (chưa bao gồm phí tàu/du thuyền)",
        highlights = listOf(
            "Du thuyền ngủ đêm cao cấp trên vịnh sang trọng",
            "Khám phá Hang Sửng Sốt - hang động lớn và đẹp bậc nhất vịnh",
            "Chèo thuyền kayak vượt qua các động luồn thơ mộng",
            "Ngắm hoàng hôn lãng mạn lặn dần sau núi đá vôi"
        ),
        foodieAdvice = listOf("Chả mực giã tay ăn kèm xôi nóng", "Sá sùng xào tỏi thơm nức", "Bún bề bề đậm vị biển khơi"),
        gradientColors = listOf(SeaBlue, JungleGreen)
    ),
    Destination(
        id = "hoian",
        nameVn = "Phố Cổ Hội An",
        nameEn = "Hoi An Ancient Town",
        province = "Quảng Nam",
        region = Region.CENTRAL,
        type = TravelType.ANCIENT_TOWN,
        rating = 4.9,
        shortDesc = "Đô thị cổ kính bình yên bên dòng sông Thu Bồn nổi tiếng, lung linh với hàng vạn chiếc đèn lồng huyền ảo.",
        longDesc = "Hội An là một đô thị cổ nằm ở hạ lưu sông Thu Bồn, thuộc vùng đồng bằng ven biển tỉnh Quảng Nam. Nhờ những chính sách bảo tồn hiệu quả, khu phố cổ hầu như vẫn giữ được nguyên vẹn kiến trúc cổ kính với mái ngói rêu phong, những bức tường vàng đặc trưng cùng lối sống mộc mạc, bình dị của người dân bản địa.",
        address = "Thành phố Hội An, Tỉnh Quảng Nam, Việt Nam",
        bestTime = "Tháng 2 đến tháng 4 (Nhiệt độ ấm áp, trời ít mưa, gió mát)",
        ticketPrice = "120.000 VNĐ / vé khách quốc tế, 80.000 VNĐ / vé khách Việt Nam",
        highlights = listOf(
            "Chiêm ngưỡng Chùa Cầu uốn lượn cổ kính - biểu tượng Hội An",
            "Thả hoa đăng cầu bình an và đi thuyền trên sông Thu Bồn",
            "Check-in với những con hẻm vàng rực hoa giấy",
            "Khám phá vẻ đẹp lung linh của phố cổ về đêm khi lên đèn"
        ),
        foodieAdvice = listOf("Bánh mì Phượng giòn ngon độc đáo", "Cao lầu Hội An chuẩn vị", "Nước mót thảo mộc thanh lọc mát lành"),
        gradientColors = listOf(GoldWarm, RedFlag)
    ),
    Destination(
        id = "sapa",
        nameVn = "Sa Pa mờ sương",
        nameEn = "Foggy Sapa Town",
        province = "Lào Cai",
        region = Region.NORTH,
        type = TravelType.MOUNTAIN,
        rating = 4.8,
        shortDesc = "Thị trấn mù sương ẩn mình trong dãy Hoàng Liên Sơn, nổi bật với Fansipan – nóc nhà Đông Dương hùng vĩ.",
        longDesc = "Sa Pa là địa danh nghỉ dưỡng vùng cao nổi tiếng bậc nhất nằm ở phía Tây Bắc Tổ quốc. Ở độ cao trung bình 1.500m – 1.800m so với mực nước biển, nơi đây được trời phú cho bầu không khí mát mẻ dễ chịu bốn mùa và bức họa thiên nhiên kì ảo với núi non điệp trùng, thung lũng lúa vàng óng ả.",
        address = "Thị xã Sa Pa, Tỉnh Lào Cai, Việt Nam",
        bestTime = "Tháng 9 đến tháng 11 (Mùa lúa chín vàng rực rỡ) hoặc tháng 3 đến tháng 5 (Mùa hoa nở đầy rừng)",
        ticketPrice = "Miễn phí ra vào thị trấn (Cáp treo Fansipan: 800.000 VNĐ/vé khứ hồi)",
        highlights = listOf(
            "Chinh phục đỉnh Fansipan bằng cáp treo đạt nhiều kỷ lục thế giới",
            "Gặp gỡ và trải nghiệm đời sống văn hóa bản làng Cát Cát, Tả Van",
            "Đi bộ trekking qua thung lũng Mường Hoa thơ mộng",
            "Thưởng thức không khí lạnh tuyết rơi hiếm có vào mùa đông"
        ),
        foodieAdvice = listOf("Lẩu cá hồi Tây Bắc tươi rói", "Thịt trâu gác bếp đậm vị mắc khén", "Bánh hạt dẻ nóng hổi"),
        gradientColors = listOf(JungleGreen, GoldWarm)
    ),
    Destination(
        id = "muine",
        nameVn = "Mũi Né",
        nameEn = "Mui Ne Sands & Resort",
        province = "Bình Thuận",
        region = Region.SOUTH,
        type = TravelType.BEACH,
        rating = 4.6,
        shortDesc = "Thiên đường biển xanh cát trắng nắng vàng sở hữu những đồi cát bay tựa sa mạc Sahara thu nhỏ tuyệt đẹp.",
        longDesc = "Mũi Né là trung tâm du lịch nổi tiếng của thành phố Phan Thiết, nổi tiếng với dải bờ biển dài sóng trong xanh xanh ngắt cùng hàng dừa nghiêng bóng mát rượi. Nét đặc sắc nhất của Mũi Né chính là những đồi cát di động khổng lồ liên tục thay đổi hình dạng theo chiều gió thổi.",
        address = "Phường Mũi Né, Thành phố Phan Thiết, Tỉnh Bình Thuận, Việt Nam",
        bestTime = "Tháng 11 đến tháng 4 sang năm (Biển lộng gió, cát mịn và sóng êm)",
        ticketPrice = "Miễn phí hoàn toàn (Các trò chơi trượt cát có phí thuê thảm nhỏ khoảng 20k)",
        highlights = listOf(
            "Chinh phục Đồi Cát Bay huyền thoại bằng mô tô địa hình",
            "Dạo bước khám phá rặng đất sét vàng đỏ độc đáo tại Suối Tiên",
            "Tham quan Làng chài Mũi Né nhộn nhịp tàu bè cập bến lúc sớm mai",
            "Trải nghiệm lướt ván diều chuyên nghiệp bậc nhất Đông Nam Á"
        ),
        foodieAdvice = listOf("Bánh xèo Phan Thiết giòn rụm", "Lẩu thả Mũi Né độc đáo", "Răng bực nướng muối ớt cay xè"),
        gradientColors = listOf(GoldWarm, SeaBlue)
    ),
    Destination(
        id = "phuquoc",
        nameVn = "Đảo Ngọc Phú Quốc",
        nameEn = "Phu Quoc Emerald Island",
        province = "Kiên Giang",
        region = Region.SOUTH,
        type = TravelType.BEACH,
        rating = 4.9,
        shortDesc = "Hòn đảo thiên đường ngập nắng với bờ cát trắng mịn màng như kem cùng hoàng hôn sắc cam lãng mạn.",
        longDesc = "Phú Quốc nằm trọn trong vịnh Thái Lan, là hòn đảo lớn nhất của Việt Nam. Được mệnh danh là Đảo Ngọc, Phú Quốc quyến rũ du khách thập phương nhờ những bãi biển xếp hạng top thế giới như Bãi Sao, Bãi Khem, hệ sình thái san hô phong phú bậc nhất cùng các khu vui chơi giải trí tầm cỡ quốc tế.",
        address = "Thành phố Phú Quốc, Tỉnh Kiên Giang, Việt Nam",
        bestTime = "Tháng 11 đến tháng 4 (Mùa khô ráo, nắng vàng chan hòa trên biển)",
        ticketPrice = "Miễn phí tắm biển công cộng (Các khu Safari, VinWonders có vé riêng khoảng 900k)",
        highlights = listOf(
            "Tắm biển tại Bãi Sao với cát trắng tinh như bột và nước biển ngọc bích",
            "Khám phá công viên bảo tồn bán hoang dã Vinpearl Safari quy mô lớn",
            "Đi cáp treo Hòn Thơm vượt biển dài nhất thế giới",
            "Khám phá vương quốc loài cá và lặn ngắm san hô quý hiếm"
        ),
        foodieAdvice = listOf("Gỏi cá trích cuốn bánh tráng", "Bún quậy Phú Quốc độc lạ", "Nhum biển nướng mỡ hành béo ngậy"),
        gradientColors = listOf(SeaBlue, SeaBlueDark)
    ),
    Destination(
        id = "dalat",
        nameVn = "Thành Phố Đà Lạt",
        nameEn = "Da Lat City of Love",
        province = "Lâm Đồng",
        region = Region.CENTRAL,
        type = TravelType.NATURE,
        rating = 4.7,
        shortDesc = "Thành phố ngàn hoa thanh bình lãng mạn với những đồi thông reo trong sương mù và khí hậu se lạnh dịu mộng.",
        longDesc = "Nằm trên cao nguyên Lâm Viên xanh mướt mát lành ở độ cao 1.500m, Đà Lạt mang đặc trưng khí hậu ôn đới khác biệt hoàn toàn với cái nóng nhiệt đới Việt Nam. Nơi đây gắn liền với những câu chuyện tình lãng mạn, kiến trúc dinh thự Pháp cổ kính xen lẫn rừng thông, hoa cỏ khoe sắc rực rỡ quanh năm.",
        address = "Thành phố Đà Lạt, Tỉnh Lâm Đồng, Việt Nam",
        bestTime = "Tháng 11 đến tháng 3 (Mùa hoa dã quỳ, mai anh đào khoe sắc, thời tiết mát mẻ khô dẻo)",
        ticketPrice = "Tùy thuộc vào từng khu du lịch (như Thung lũng Tình yêu từ 250.000 VNĐ)",
        highlights = listOf(
            "Săn mây huyền ảo lãng đãng lúc bình minh tại Đồi chè Cầu Đất",
            "Dạo quanh Hồ Xuân Hương thơ mộng hít hà không khí tịnh thanh",
            "Thăm Thung lũng Tình Yêu ngập tràn các tác phẩm từ hoa tươi",
            "Khám phá ga xe lửa Đà Lạt cổ kính nhất Đông Dương"
        ),
        foodieAdvice = listOf("Bánh tráng nướng Đà Lạt (Pizza Việt Nam)", "Lẩu gà lá é ấm nồng", "Kem bơ sáp béo bùi ngon miệng"),
        gradientColors = listOf(JungleGreen, RedFlag)
    ),
    Destination(
        id = "hue",
        nameVn = "Cố Đô Huế",
        nameEn = "Hue Imperial Ancient City",
        province = "Thừa Thiên Huế",
        region = Region.CENTRAL,
        type = TravelType.HISTORY,
        rating = 4.8,
        shortDesc = "Địa danh lịch sử linh thiêng nơi bảo tồn nguyên vẹn quần thể cung điện lăng tẩm oai nghiêm vương triều Nguyễn.",
        longDesc = "Huế là thành phố di sản cổ kính nồng nàn mang trong mình bề dày lịch sử vàng son làm kinh đô triều Nguyễn kéo dài hơn một thế kỷ. Nét đẹp trầm mặc, trữ tình của xứ Huế thơ mộng in sâu vào Đại Nội cổ kính, những lăng tẩm rêu phong uy nghiêm đan xen nhịp sống chậm rãi thanh bình bên dòng sông Hương thơ mộng.",
        address = "Thành phố Huế, Tỉnh Thừa Thiên Huế, Việt Nam",
        bestTime = "Tháng 1 đến tháng 4 (Thời tiết mát mẻ dễ chịu trước khi nắng nóng gay gắt)",
        ticketPrice = "Khách người lớn: 200.000 VNĐ / vé tham quan Hoàng Cung Huế",
        highlights = listOf(
            "Khám phá hoàng cung Đại Nội lộng lẫy uy nghiêm vàng son một thuở",
            "Tĩnh lặng ngồi thuyền nghe Ca Huế đặc sản văn hóa phi vật thể quốc gia",
            "Viếng ngôi chùa linh thiêng bậc nhất xứ Huế - Chùa Thiên Mụ cổ kính",
            "Chiêm ngưỡng kiến trúc lăng tẩm đặc sắc độc bản của vua Khải Định"
        ),
        foodieAdvice = listOf("Bún bò Huế cay nồng chuẩn vị xứ cố đô", "Bánh bột lọc mướt mát cuốn miệng tôm thịt", "Chè cung đình Huế phong phú 36 món ngon thơm ngọt"),
        gradientColors = listOf(RedDeep, GoldWarm)
    ),
    Destination(
        id = "ninhbinh",
        nameVn = "Tràng An - Ninh Bình",
        nameEn = "Ninh Binh Karst Landscape",
        province = "Ninh Bình",
        region = Region.NORTH,
        type = TravelType.NATURE,
        rating = 4.8,
        shortDesc = "\"Vịnh Hạ Long trên cạn\" với dãy núi đá vôi uốn lượn kỳ diệu bên dòng nước uốn quanh mùa lúa chín vàng óng.",
        longDesc = "Quần thể danh thắng Tràng An được UNESCO ghi danh là Di sản hỗn hợp văn hóa lẫn thiên nhiên đầu tiên của Việt Nam. Cảnh quan nơi đây là kiệt tác địa chất hoành tráng kết hợp trọn vẹn giữa núi đá vôi dựng đứng cheo leo, thung lũng nước xanh thẳm huyền hoặc và các đền miếu linh thiêng hàng nghìn năm tuổi phong sương cổ điển.",
        address = "Huyện Hoa Lư, Tỉnh Ninh Bình, Việt Nam",
        bestTime = "Tháng 2 đến tháng 6 (Tuyệt diệu lúc xuân sang lễ hội hoa rừng và lúa chín tháng 5 đẹp ảo diệu)",
        ticketPrice = "250.000 VNĐ / người lớn (đã bao gồm dịch vụ đi thuyền đò ngắm cảnh xuyên sơn)",
        highlights = listOf(
            "Đi đò chèo thong thả xuyên qua các hang luồn nước mông mênh lung linh kì vĩ",
            "Chinh phục đỉnh núi Ngọa Long tại Hang Múa ngắm toàn cảnh Tam Cốc thơ mộng",
            "Ghé thăm đền thờ cổ kính linh thiêng trong không gian u tĩnh thanh bình",
            "Hành hương tới chùa Bái Đính nguy nga đồ sộ bậc nhất châu Á"
        ),
        foodieAdvice = listOf("Cơm cháy Ninh Bình giòn tan thơm lừng", "Thịt dê núi nướng ngũ vị đậm đà", "Nem chua Yên Mạc lạ miệng"),
        gradientColors = listOf(JungleGreen, SeaBlue)
    ),
    Destination(
        id = "cantho",
        nameVn = "Thủ Phủ Cần Thơ",
        nameEn = "Can Tho - Mekong Delta Heart",
        province = "Cần Thơ",
        region = Region.SOUTH,
        type = TravelType.NATURE,
        rating = 4.6,
        shortDesc = "Trải nghiệm văn hóa sông nước miền Tây hữu tình, chợ nổi Cái Răng nhộn nhịp xuồng ghe dập dìu quả ngọt bốn mùa.",
        longDesc = "Cần Thơ tự hào là trung tâm kinh tế xã hội nổi bật đại diện cho dải đất chín rồng Tây Nam Bộ trù phú. Giới mộ điệu yêu mến Cần Thơ nhờ khí hậu sông nước thuận hòa hiền dịu, vườn trái cây nhiệt đới trĩu cành say quả, và đặc biệt là nhịp sống giao thương mưu sinh đầy hứng khởi dập dìu ghe xuồng trên sông Mekong anh dũng rực rỡ thuở sơ khai.",
        address = "Thành phố Cần Thơ, Việt Nam",
        bestTime = "Tháng 12 đến tháng 4 năm sau (Mùa thu hoạch dâu miền Tây, bọ rùa mùa nước nổi và dã ngoại sông cực đẹp)",
        ticketPrice = "Miễn phí (Thuê xuồng hoặc can ty du lịch ngắm chợ nổi tùy theo đoàn mức dạo 300k - 500k VNĐ)",
        highlights = listOf(
            "Dậy thật sớm đón bình minh tuyệt ảo trên chợ nổi Cái Răng tấp nập sầm uất",
            "Rảo bước ngắm cảnh hoàng hôn quyến rũ trên cầu đi bộ tình yêu Bến Ninh Kiều cổ",
            "Hành trình quay ngược ký ức xưa cũ với phong cách Pháp - Hoa độc đáo tại Nhà cổ Bình Thủy",
            "Tự tay hái và thưởng thức dã dượi chôm chôm, măng cụt, dâu da ngay tại miệt vườn xum xuê"
        ),
        foodieAdvice = listOf("Lẩu mắm miền Tây đậm đà mỡ hành", "Bánh xèo khổng lồ thơm ngậy nước cốt dừa", "Hủ tiếu chiên giòn rụm (Pizza Hủ Tiếu) độc môn"),
        gradientColors = listOf(SeaBlueDark, GoldWarm)
    ),
    Destination(
        id = "condao",
        nameVn = "Côn Đảo hoang sơ",
        nameEn = "Con Dao Clean Paradise",
        province = "Bà Rịa – Vũng Tàu",
        region = Region.SOUTH,
        type = TravelType.BEACH,
        rating = 4.8,
        shortDesc = "Trái tim lịch sử linh thiêng hào hùng dân tộc, một thiên đường biển đảo hoang sơ quyến rũ xếp hạng quốc tế.",
        longDesc = "Côn Đảo sở hữu hai thái cực đầy cảm xúc: Di tích nhà tù Côn Đảo oai hùng đẫm máu sương xưa cũ cứu quốc và một thế giới thiên nhiên sinh quyển hoang dã bạt ngàn tuyệt đẹp. Giới lữ hành bình chọn đây là một trong những hòn đảo bí hiểm và xinh đẹp bậc nhất hành tinh với bãi cát vắng u yên thanh, rừng ngập mặn đồi núi hoang sơ.",
        address = "Huyện Côn Đảo, Tỉnh Bà Rịa – Vũng Tàu, Việt Nam",
        bestTime = "Tháng 3 đến tháng 9 (Gió êm, rùa biển đẻ trứng, sóng êm ái)",
        ticketPrice = "Miễn phí các bãi biển (Vé thăm di tích nhà tù đồng giá 50.000 VNĐ)",
        highlights = listOf(
            "Viếng Nghĩa trang Hàng Dương viếng mộ nữ anh hùng Võ Thị Sáu linh thiêng vào ban đêm",
            "Mục sở thị quần thể di tích chuồng cọp ghê rợn hào hùng giữ lửa đấu tranh cách mạng",
            "Nằm dài tận hưởng bãi tắm Đầm Trầu hoang dã, ngắm máy bay hạ cánh cực sát đỉnh đầu sát bãi cát",
            "Lặn biển ngắm thảm san hô nguyên thủy rực rỡ quý hiếm ở Hòn Tài, Hòn Bảy Cạnh"
        ),
        foodieAdvice = listOf("Cháo hàu nóng hổi thơm nức béo ngậy", "Cá thu một nắng rán vàng thơm phức", "Mứt hạt bàng rang muối giòn sần sật mặn ngọt độc lạ"),
        gradientColors = listOf(SeaBlue, SeaBlueDark)
    ),
    Destination(
        id = "mucangchai",
        nameVn = "Mù Cang Chải",
        nameEn = "Mu Cang Chai Terraces",
        province = "Yên Bái",
        region = Region.NORTH,
        type = TravelType.MOUNTAIN,
        rating = 4.7,
        shortDesc = "Kiệt tác ruộng bậc thang uốn lượn u sầu tuyệt diệu như nấc thang đưa du khách chạm đến mây ngàn Tây Bắc.",
        longDesc = "Mù Cang Chải tạc nên một kỳ tích nông nghiệp dệt từ đất và mồ hôi của đồng bào dân tộc H’Mông kỳ vĩ. Những thửa ruộng bậc thang trùng trùng điệp điệp trải dài phủ khắp sườn núi tạo ra bức tranh cảnh sắc tráng lệ rung động lòng người, đặc biệt vào mùa nước đổ long lanh lấp lánh và mùa vàng lúa gặt rạng ngời rực rỡ sơn hà.",
        address = "Huyện Mù Cang Chải, Tỉnh Yên Bái, Việt Nam",
        bestTime = "Tháng 9 đến tháng 10 (Mùa vàng rực rực thu hoạch đầy lúa nếp gặt hái rộn rã vinh quang Tây Bắc)",
        ticketPrice = "Miễn phí hoàn toàn cảnh thiên nhiên bao la tươi đẹp",
        highlights = listOf(
            "Chiêm ngưỡng 'mâm xôi vàng' trứ danh tại vùng La Pán Tẩn quyến rũ",
            "Chinh phụ đèo Khâu Phạ lẫy lừng - một trong tứ đại đỉnh đèo hiểm trở",
            "Thử thách dù lượn mạo hiểm 'Bay trên mùa vàng' ngắm mây gió hùng vĩ",
            "Tìm hiểu nếp văn hóa bản làng người Thái, người Mông mộc mạc nguyên sơ"
        ),
        foodieAdvice = listOf("Xôi ngũ sắc làm từ nếp nương thơm mềm dẻo dai", "Cá suối nướng muối mắc khén thơm ngọt", "Lạp xưởng hun khói gác bếp Tây Bắc"),
        gradientColors = listOf(GoldWarm, JungleGreen)
    ),
    Destination(
        id = "banahills",
        nameVn = "Bà Nà Hills - Cầu Vàng",
        nameEn = "Ba Na Hills & Golden Bridge",
        province = "Đà Nẵng",
        region = Region.CENTRAL,
        type = TravelType.MOUNTAIN,
        rating = 4.9,
        shortDesc = "Kiệt tác dạo bước trên mây nổi danh toàn cầu với cây Cầu Vàng cổ tích nâng đỡ mềm mại bởi bàn tay khổng lồ kiêu kỳ.",
        longDesc = "Bà Nà Hills tọa lạc tại đỉnh cao 1.487m so với mực nước biển trên dãy Trường Sơn thơ mộng, mang đến hệ thời tiết mát mẻ quanh năm bồng bềnh 4 mùa trong 1 ngày duy nhất cực lý thú. Điểm thu hút hàng triệu sự chú ý du khách là cây Cầu Vàng độc nhất vô nhị nâng đỡ bởi đôi bàn tay khổng lồ u ám rêu phong mọc ra từ thung lũng xanh bạt ngàn kỳ ảo.",
        address = "Xã Hòa Ninh, Huyện Hòa Vang, Thành phố Đà Nẵng, Việt Nam",
        bestTime = "Tháng 3 đến tháng 9 (Trời quang mây tịnh, chụp hình Cầu Vàng rõ ảo rực rỡ nhất phong lưu)",
        ticketPrice = "900.000 VNĐ / người lớn (bao gồm khứ hồi cáp treo và vui chơi thỏa thích tại Fantasy Park)",
        highlights = listOf(
            "Tự do dạo bước săn góc hình check-in triệu like huyền ảo trên Cầu Vàng",
            "Trải nghiệm đi dạo thám hiểm quảng trường Làng Pháp mộng mơ tuyết kính cổ điển",
            "Ngắm nhìn dãy núi non Trường Sơn bạt ngàn đẹp mắt từ hệ thống cáp treo nhiều kỷ lục toàn cầu",
            "Hòa mình tưng bừng vào lễ hội carnival châu Âu sôi động diễu hành hằng ngày"
        ),
        foodieAdvice = listOf("Xúc xích tươi nướng kiểu Đức giòn thắm", "Món bia tươi cực lạnh sảng khoái trên núi cao", "Buffet ẩm thực phong phú đa dạng Á Âu tại các nhà hàng"),
        gradientColors = listOf(SeaBlue, RedFlag)
    )
)
