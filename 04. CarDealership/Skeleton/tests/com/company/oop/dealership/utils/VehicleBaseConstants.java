package com.company.oop.dealership.utils;

import static com.company.oop.dealership.utils.TestUtilities.getString;

public class VehicleBaseConstants {

        public static final int MAKE_NAME_LEN_MIN = 2;
        public static final int MODEL_NAME_LEN_MIN = 1;
        public static final double PRICE_VAL_MIN = 0;

        public static final String VALID_MAKE = getString(MAKE_NAME_LEN_MIN + 1);
        public static final String VALID_MODEL = getString(MODEL_NAME_LEN_MIN + 1);
        public static final double VALID_PRICE = PRICE_VAL_MIN + 1;
        public static final String INVALID_MAKE = getString(MAKE_NAME_LEN_MIN - 1);
        public static final String INVALID_MODEL = getString(MODEL_NAME_LEN_MIN - 1);
}