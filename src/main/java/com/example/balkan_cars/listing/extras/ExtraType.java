package com.example.balkan_cars.listing.extras;

public enum ExtraType {

    // 🛡️ SAFETY
    AUTOMATIC_STABILITY_CONTROL(ExtraCategory.SAFETY),
    ADAPTIVE_FRONT_LIGHTS(ExtraCategory.SAFETY),
    ANTI_LOCK_BRAKING_SYSTEM(ExtraCategory.SAFETY),
    REAR_AIRBAGS(ExtraCategory.SAFETY),
    FRONT_AIRBAGS(ExtraCategory.SAFETY),
    SIDE_AIRBAGS(ExtraCategory.SAFETY),
    ELECTRONIC_BRAKE_FORCE_DISTRIBUTION(ExtraCategory.SAFETY),
    ELECTRONIC_STABILITY_PROGRAM(ExtraCategory.SAFETY),
    TIRE_PRESSURE_MONITORING(ExtraCategory.SAFETY),
    PARKTRONIC(ExtraCategory.SAFETY),
    ISOFIX_SYSTEM(ExtraCategory.SAFETY),
    DYNAMIC_STABILITY_CONTROL(ExtraCategory.SAFETY),
    TRACTION_CONTROL_SYSTEM(ExtraCategory.SAFETY),
    BRAKE_PAD_DRYING_SYSTEM(ExtraCategory.SAFETY),
    DISTANCE_CONTROL_SYSTEM(ExtraCategory.SAFETY),
    HILL_DESCENT_CONTROL(ExtraCategory.SAFETY),
    BRAKE_ASSIST_SYSTEM(ExtraCategory.SAFETY),

    // 🚗 EXTERIOR
    DOORS_4_5(ExtraCategory.EXTERIOR),
    LED_HEADLIGHTS(ExtraCategory.EXTERIOR),
    XENON_HEADLIGHTS(ExtraCategory.EXTERIOR),
    ALLOY_WHEELS(ExtraCategory.EXTERIOR),
    METALLIC_PAINT(ExtraCategory.EXTERIOR),
    PANORAMIC_SUNROOF(ExtraCategory.EXTERIOR),
    ROOF_RAILS(ExtraCategory.EXTERIOR),

    // 🔒 SECURITY
    ALARM_SYSTEM(ExtraCategory.SECURITY),
    CENTRAL_LOCKING(ExtraCategory.SECURITY),

    // 🪑 INTERIOR
    VELOUR_UPHOLSTERY(ExtraCategory.INTERIOR),
    LEATHER_UPHOLSTERY(ExtraCategory.INTERIOR),

    // 😌 COMFORT
    AUTO_START_STOP(ExtraCategory.COMFORT),
    KEYLESS_START(ExtraCategory.COMFORT),
    POWER_MIRRORS(ExtraCategory.COMFORT),
    POWER_WINDOWS(ExtraCategory.COMFORT),
    ELECTRIC_SUSPENSION_ADJUSTMENT(ExtraCategory.COMFORT),
    ELECTRIC_SEAT_ADJUSTMENT(ExtraCategory.COMFORT),
    POWER_STEERING(ExtraCategory.COMFORT),
    CLIMATRONIC(ExtraCategory.COMFORT),
    MULTIFUNCTION_STEERING_WHEEL(ExtraCategory.COMFORT),
    STEERING_WHEEL_ADJUSTMENT(ExtraCategory.COMFORT),
    RAIN_SENSOR(ExtraCategory.COMFORT),
    LIGHT_SENSOR(ExtraCategory.COMFORT),
    HEADLIGHT_WASHER_SYSTEM(ExtraCategory.COMFORT),
    CRUISE_CONTROL(ExtraCategory.COMFORT),
    BOARD_COMPUTER(ExtraCategory.COMFORT),
    COOLING_GLOVEBOX(ExtraCategory.COMFORT),

    // 🎵 MULTIMEDIA
    BLUETOOTH_HANDSFREE(ExtraCategory.MULTIMEDIA),
    STEPTRONIC_TIPTRONIC(ExtraCategory.MULTIMEDIA),
    USB_AUDIO_VIDEO_INPUT(ExtraCategory.MULTIMEDIA),
    NAVIGATION_SYSTEM(ExtraCategory.MULTIMEDIA),
    STEREO_SYSTEM(ExtraCategory.MULTIMEDIA),

    // 📄 DOCUMENTATION
    SERVICE_BOOK(ExtraCategory.DOCUMENTATION),
    NEW_IMPORT(ExtraCategory.DOCUMENTATION),

    // 💰 FINANCE
    LEASING(ExtraCategory.FINANCE),

    // 🧩 OTHER
    OTHER(ExtraCategory.OTHER);

    private final ExtraCategory category;

    ExtraType(ExtraCategory category) {
        this.category = category;
    }

    public ExtraCategory getCategory() {
        return category;
    }
}
