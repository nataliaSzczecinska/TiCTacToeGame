package com.tictactoe.game;

public enum EnumResults {
        X('X'),
        O('O'),
        d('d'),
        n('n'),
        empty(' ');

        private char sign;

        EnumResults(char sign) {
                this.sign = sign;
        }

        public static EnumResults enumValue(char sign) {
                switch (sign) {
                        case ' ': {
                                return empty;
                        } case 'n': {
                                return n;
                        } case 'd': {
                                return d;
                        } case 'X': {
                                return X;
                        }
                        default: {
                                return O;
                        }
                }
        }

        public char getSign() {
                return sign;
        }

        @Override
        public String toString() {
                return "EnumResults{" +
                        "sign=" + sign +
                        '}';
        }
}
