package com.pgg;

/**
 * Created by Carina on 04/12/2016.
 */
public class MinMax {
        private final int min;
        private final int max;
        private final int totalMin;
        private final int totalMax;

        public MinMax(int min, int max, int totalMin, int totalMax) {
            this.min = min;
            this.max = max;
            this.totalMax = totalMax;
            this.totalMin = totalMin;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }

        public int getTotalMin(){   return totalMin;}
        public int getTotalMax(){ return totalMax;}

}
