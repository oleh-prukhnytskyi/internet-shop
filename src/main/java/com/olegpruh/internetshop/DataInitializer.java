package com.olegpruh.internetshop;

import com.olegpruh.internetshop.model.*;
import com.olegpruh.internetshop.security.RoleName;
import com.olegpruh.internetshop.service.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Component
public class DataInitializer {
    private final UserService userService;
    private final ProductService productService;
    private final ProductImageService productImageService;
    private final RoleService roleService;
    private final DeliveryOptionService deliveryOptionService;
    private static final boolean IS_ENABLED = false;

    public DataInitializer(UserService userService, ProductService productService,
                           ProductImageService productImageService, RoleService roleService,
                           DeliveryOptionService deliveryOptionService) {
        this.userService = userService;
        this.productService = productService;
        this.productImageService = productImageService;
        this.roleService = roleService;
        this.deliveryOptionService = deliveryOptionService;
    }

    @PostConstruct
    public void inject() {
        if (!IS_ENABLED) {
            return;
        }

        DeliveryOption freeDelivery = new DeliveryOption();
        freeDelivery.setTitle("FREE Delivery");
        freeDelivery.setDescription("(3-5 days)");
        freeDelivery.setPrice(BigDecimal.ZERO);
        deliveryOptionService.save(freeDelivery);

        DeliveryOption premiumDelivery = new DeliveryOption();
        premiumDelivery.setTitle("Premium Delivery");
        premiumDelivery.setPrice(BigDecimal.valueOf(4.99));
        premiumDelivery.setDescription("(1-2 days)");
        deliveryOptionService.save(premiumDelivery);

        DeliveryOption fastDelivery = new DeliveryOption();
        fastDelivery.setTitle("Extra Fast Delivery");
        fastDelivery.setDescription("get it today");
        fastDelivery.setPrice(BigDecimal.valueOf(5.99));
        deliveryOptionService.save(fastDelivery);

        User admin = new User();
        Role adminRole = new Role(RoleName.ADMIN);
        roleService.save(adminRole);
        admin.setEmail("admin@i.ua");
        admin.setPassword("admin");
        admin.setRoles(Set.of(adminRole));
        userService.save(admin);

        User user = new User();
        Role userRole = new Role(RoleName.USER);
        roleService.save(userRole);
        user.setEmail("user@i.ua");
        user.setPassword("user");
        user.setRoles(Set.of(userRole));
        userService.save(user);

        User seller = new User();
        Role sellerRole = new Role(RoleName.SELLER);
        roleService.save(sellerRole);
        seller.setEmail("seller@i.ua");
        seller.setPassword("seller");
        seller.setRoles(Set.of(sellerRole));
        userService.save(seller);

        User courier = new User();
        Role courierRole = new Role(RoleName.COURIER);
        roleService.save(courierRole);
        courier.setEmail("courier@i.ua");
        courier.setPassword("courier");
        courier.setRoles(Set.of(courierRole));
        userService.save(courier);

        ProductImage productImage1 = new ProductImage("1823749123.webp");
        Product product1 = new Product("VARMILO VA88M V2 MOONLIGHT UK ISO CHERRY MX BROWN",
                BigDecimal.valueOf(140.00), List.of(productImageService.save(productImage1)),"The VA88M V2 Moonlight is a high quality, tenkeyless, mechanical,"
                + " PC keyboard by Varmilo it has a charcoal case, black Iron base plate, white backlighting and "
                + "the keys are dark and light grey with three cyan keys. The legends are black and dye-sublimated"
                + " onto the PBT keycaps in a UK ISO layout.");
        productService.add(product1);

        ProductImage productImage2 = new ProductImage("234235562.jpg");
        Product product2 = new Product("ATWG Butterfly Trainer Knife Pocket Practice Training Knifes Blade Tool - NOT REAL - NO SHARP",
                BigDecimal.valueOf(9.99), List.of(productImageService.save(productImage2)),"Total length: 220mm , blade length:90mm , weight : 130g\n" +
                "Stainless steel material ,broken-resistant and durable; Without edge, and risk-free\n" +
                "Use in outdoors , camping and hiking ; also can use for collecting and giving gift\n" +
                "The blade is designed in sword shape ,no edged , it is very safe . Streamlined handle design, non-slip surface treatment, with buckle, easy to carry\n" +
                "A good practice tool for beginner ,will get more fun while playing"
                + " onto the PBT keycaps in a UK ISO layout.");
        productService.add(product2);

        ProductImage productImage3 = new ProductImage("2873642.jpg");
        Product product3 = new Product("Logitech G502 HERO High Performance Wired Gaming Mouse, HERO 25K Sensor, 25,600 DPI, RGB, Adjustable Weights, 11 Programmable Buttons, On-Board Memory, PC/Mac - Black",
                BigDecimal.valueOf(34.99), List.of(productImageService.save(productImage3)),"HERO Gaming Sensor: Next generation HERO mouse sensor delivers precision tracking up to 25600 DPI with zero smoothing, filtering or acceleration\n" +
                "11 programmable buttons and dual mode hyper-fast scroll wheel: The Logitech wired gaming mouse gives you fully customisable control over your gameplay\n" +
                "Adjustable weights: Match your playing style. Arrange up to five 3.6 g weights for a personalised weight and balance configuration\n" +
                "LIGHTSYNC technology: Logitech G LIGHTSYNC technology provides fully customisable RGB lighting that can also synchronise with your gaming"
                + " onto the PBT keycaps in a UK ISO layout.");
        productService.add(product3);

        ProductImage productImage4 = new ProductImage("32907348722.jpg");
        Product product4 = new Product("UMTMedia® 2 X Ultrasonic Module HC-SR04 Distance Range Transducer Sensor For Arduino PI",
                BigDecimal.valueOf(3.90), List.of(productImageService.save(productImage4)),"Adopts IO port trigger for at least 10us high level signal\n" +
                "It can send eight 40khz square wave automatically, and detect if there is any signal returned\n" +
                "If the signal returning, output one high level signal via IO port\n" +
                "The duration of the high level signal is the time from sending ultrasonic to returning\n" +
                "Measuring distance=duration of high level x sound velocity(340m/s)/2");
        productService.add(product4);

        ProductImage productImage5 = new ProductImage("2342557235.jpg");
        Product product5 = new Product("HyperX Alloy Origins Core – RGB Gaming Mechanical Keyboard, Tenkeyless, HyperX Red switches (US layout)",
                BigDecimal.valueOf(80.28), List.of(productImageService.save(productImage5)),"HyperX Mechanical Switches rated for 80 million keystrokes; Total Travel: 3.8 mm\n" +
                "Full aircraft-grade aluminum body\n" +
                "Ultra-compact TKL design with detachable USB Type-C cable\n" +
                "Dazzling RGB lighting with dynamic effects\n" +
                "Advanced customization with HyperX NGENUITY software");
        productService.add(product5);

        ProductImage productImage6 = new ProductImage("928374.jpg");
        Product product6 = new Product("Razer Kraken - Cross-Platform Wired Gaming Headset (Custom Tuned 50mm Drivers, Unidirectional Microphone, 3.5mm Cable with In-line controls, Cross Platform Compatible) Black",
                BigDecimal.valueOf(61.23), List.of(productImageService.save(productImage6)),"CUSTOM-TUNED 50 MM DRIVERS: Provides a wide soundscape, from subtle footsteps sneaking up behind you to climatic explosions that blow you away.\n" +
                "COOLING GEL-INFUSED EAR CUSHIONS: Reduces heat build-up, while a soft cloth and leatherette combination provides comfort and sound isolation, so you can enjoy gaming for hours.\n" +
                "UNIDIRECTIONAL RETRACTABLE MICROPHONE: Ensure your shotcalls are always delivered in absolute clarity\n" +
                "BAUXITE ALUMINUM FRAME WITH THICKER PADDING: For light weight, durability and flexibility. The headband padding relieves pressure on your head, so the headset feels even lighter.\n" +
                "CROSS-PLATFORM COMPATIBLE: Compete across your PC, PS4, Xbox One, Switch and Mobile Devices with the 3.5 mm combo jack");
        productService.add(product6);

        ProductImage productImage7 = new ProductImage("24362452345.jpg");
        Product product7 = new Product("Xiaomi Mi True Wireless Earbuds Basic 2 - Black",
                BigDecimal.valueOf(19.99), List.of(productImageService.save(productImage7)),"\"Just for fans\" - that's our idea. Our Team of My Fans leads " +
                "every step of the way. In fact, many Xiaomi employees were my fans first before joining the team. As a team, we share" +
                " the relentless pursuit of perfection, constantly refining and improving our products to create the best user experience" +
                " possible. We are also fearless in testing new ideas and pushing our own limits. Our dedication and conviction in" +
                " innovation, together with the support of Mi fans are our driving forces behind Mi products.");
        productService.add(product7);

        ProductImage productImage8 = new ProductImage("61LF6m1Ac4L._AC_SX679_.jpg");
        Product product8 = new Product("AKASO EK7000 4K30FPS Action Camera - 20MP Ultra HD Underwater Camera 170 Degree Wide Angle 98FT Waterproof Camera with Accessory Kit",
                BigDecimal.valueOf(57.99), List.of(productImageService.save(productImage8)),"4K Ultra HD Action Camera - Professioal 4K 30fps & 2.7K 30fps video winth 20MP photos at up to 30 frames per second for incredible photos, which is 4 times the resolution of traditional HD cameras. Capture and share your world clearly.\n" +
                "Wireless Remote Control - With a 2.4G remote, you can capture the world in an all-new way. Controlling the camera, framing shots or recording video is very convenient.\n" +
                "Long Battery Life - AKASO EK7000 action camera comes with 2 rechargeable 1050mAh batteries and 1 usb dual battery charger. Each battery can record up tp 90 minutes. You will no more worry about the recording time for this action camera.");
        productService.add(product8);

        ProductImage productImage9 = new ProductImage("81h2ew8lygL._AC_SX425_.jpg");
        Product product9 = new Product("iiyama G-Master GB3461WQSU-B1 34 Inch 21:9 Ultra Wide ADS-IPS LCD with HDR,144hz,1ms,3840x1440,450 cd/m² Brightness,FreeSync Premium ,2 xHDMI,2 x Display Port,2 x 5W Speakers,Height Adjustable Stand",
                BigDecimal.valueOf(408.03), List.of(productImageService.save(productImage9)),"IPS LCD TECHNOLOGY - IPS technology offers higher contrast,darker blacks and much better viewing angles than standard TN technology. The screen will look good no matter from what angle you look at it.\n" +
                "Ultra Wide - UWQHD (Ultra Wide QHD) resolution offers an ultra wide (21:9) viewable area.\n" +
                "AMD FreeSync PREMIUM - AMD FreeSync Premium take the rigorous testing certification process for screens to a new level. Ensuring tear free,low latency,high frame rate experience when gaming. Generating a smooth judder free transition of frames to maximise performance and supporting Low Frame Rate Compensation (LFC) and up to 120Hz\n" +
                "144Hz - Rapid refresh rate and frame rate guarantee smooth,blur free and judder free graphics,with lower latency and lag improving response times for fast paced games. Especially suited to the fast pace games like FPS,racers,MOBA and sports where the 144Hz refresh rate delivers the crisp and sharp images needed to raise your game.\n" +
                "GAMER CUSTOMIZATION – Get the ultimate performance when gaming by choosing one of the predefined gaming modes (including FPS and strategy) or set and memorize your own custom settings. With the ‘Black Tuner’ technology,users can adjust the brightness and the dark shades with the Black Tuner,giving greater viewing performance in shadowed areas and helping to spot the enemy earlier.");
        productService.add(product9);

        ProductImage productImage10 = new ProductImage("61zEiCKtHEL._AC_SX679_.jpg");
        Product product10 = new Product("Kyrio A2212 Brushless Motor 2200KV Outrunner Brushless Motor RC Accessories Kit with Mounts for RC Aircraft Plane Glider Quadcopter Helicopter Copter Multi-Copter",
                BigDecimal.valueOf(11.99), List.of(productImageService.save(productImage10)),"Name: Brushless Motor; Model : A2212-6; KV : 2200RPM/V\n" +
                "Material : Metal, Cable Length : 55mm / 2.2\"\n" +
                "Motor Part Size : 25 x 28mm/ 1\" x 1.1\"(L*D); Shaft Size : 3 x 12mm/ 0.1\" x 0.5\"(D*L)\n" +
                "Fit for Battery : 2-3 Li-Poly, Fit for ESC : 30A\n" +
                "Suitable for RC Glider Quadcopter Helicopter Aircraft Copter Multi-copter");
        productService.add(product10);

        ProductImage productImage11 = new ProductImage("61moNwuo20L._AC_SX679_.jpg");
        Product product11 = new Product("Zeee 3S Lipo Battery 5200mAh 11.1V 50C RC Battery with Deans Connector Compatible wit RC Plane Quadcopter RC Airplane RC Helicopter RC Car Truck Boat(Short)",
                BigDecimal.valueOf(33.99), List.of(productImageService.save(productImage11)),"【Zeee 3S battery 5200mAh specification】- Battery voltage: 11.1V; Configuration:3S1P; Capacity: 5200mAh; Discharge: 50C; Connector: Deans Plug.\n" +
                "【Dimensions】- Zeee 11.1V 50C 5200mAh Rc lipo battery dimension is (±0.08in): 5.20*1.69*0.98inch/132*43*25mm (L*W*H); Approx Weight (±0.03lb): 0.76lb/343g.\n" +
                "【Powerful Functions】- Zeee 3S battery 5200mAh has a huge capacity for extended run times and a gigantic C rating so you'll have all the power you'll ever need.\n" +
                "【Application】- Zeee 3S 5200mah lipo battery Compatible with RC helicopter, airplane, multirotor, Boat, Car, Truck, Slash VXL, Slash 4x4 VXL, E-Maxx Brushless, E-Revo Brushless and Spartan models(Only if the voltage, dimension and the plug match, then it will fit)");
        productService.add(product11);

        ProductImage productImage12 = new ProductImage("617s+ydZQXL._AC_UL1500_.jpg");
        Product product12 = new Product("HANPOSH Boys Watches for 6-15 Year Old Waterproof Analogue Quartz Children with Silicone Unisex-Kids Watch",
                BigDecimal.valueOf(10.87), List.of(productImageService.save(productImage12)),"THE CHRISTMAS GIFT FOR YOUR LOVELY KIDS: This watch dial could been choic by 5 DIFFERENT COLORS, which can attract kids to fall in love with the watch the most, and develop a good habits. It is a BETTER choice for birthday gifts or Xmas gifts and so on (Fit Wrist Perimeter Range: 140-190mm (It can be worn if the wrist is thinner, looser and more comfortable)\n" +
                "HIGH QUALITY PRODUCTION: Durable and environmentally friendly PU strap, transparent and durable glass resin mirror. Not need to worry about kids breaking the watch\n" +
                "BETTER WATERPROOF THAN DAILY USE: 30M(3ATM/100FT) Water resistant, swimming, splash, rain, resistant hand wash, shower, car wash no problem (except hot water, sauna, and diving, please DO NOT pressing the button underwater)");
        productService.add(product12);

        ProductImage productImage13 = new ProductImage("61HHS0HrjpL._AC_SX679_.jpg");
        Product product13 = new Product("Apple iPhone 14 Pro (128 GB) - Deep Purple",
                BigDecimal.valueOf(1099.00), List.of(productImageService.save(productImage13)),"6.1-inch Super Retina XDR display featuring Always-On and ProMotion\n" +
                "Dynamic Island, a magical new way to interact with iPhone\n" +
                "48MP Main camera for up to 4x greater resolution\n" +
                "Cinematic mode now in 4K Dolby Vision up to 30 fps\n" +
                "Action mode for smooth, steady, handheld videos\n" +
                "Vital safety features—Emergency SOS via satellite and Crash Detection\n" +
                "All-day battery life and up to 23 hours of video playback");
        productService.add(product13);

        ProductImage productImage14 = new ProductImage("610pghkO81L._AC_SX679_.jpg");
        Product product14 = new Product("Apple iPhone 14 Pro Max (1 TB) - Space Black",
                BigDecimal.valueOf(1749.00), List.of(productImageService.save(productImage14)),"6.7-inch Super Retina XDR display featuring Always-On and ProMotion\n" +
                "Dynamic Island, a magical new way to interact with iPhone\n" +
                "48MP Main camera for up to 4x greater resolution\n" +
                "Cinematic mode now in 4K Dolby Vision up to 30 fps\n" +
                "Action mode for smooth, steady, handheld videos\n" +
                "Vital safety features—Emergency SOS via satellite and Crash Detection\n" +
                "All-day battery life and up to 29 hours of video playback");
        productService.add(product14);

        ProductImage productImage15 = new ProductImage("71O2f4-BvjL._AC_SX425_.jpg");
        Product product15 = new Product("Apple Watch Series 3 (GPS, 38mm) - Space Grey Aluminum Case with Black Sport Band",
                BigDecimal.valueOf(179.00), List.of(productImageService.save(productImage15)),"GPS\n" +
                "Retina display\n" +
                "Swimproof\n" +
                "Optical heart sensor\n" +
                "Store music, podcasts and audiobooks\n" +
                "Emergency SOS\n" +
                "S3 chip with dual-core processor\n" +
                "watchOS with Activity trends, cycle tracking, hearing health innovations, and the App Store on your wrist\n" +
                "Aluminium case");
        productService.add(product15);
    }
}
