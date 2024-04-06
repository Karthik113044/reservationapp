package com.reservationapp.WrapperClass;

import com.reservationapp.entity.Route;
import com.reservationapp.entity.SubRoute;

import java.util.List;

public class wrapperClass {

    public static class DataWrapper {
        private List<Route> routes;
        private List<SubRoute> subRoutes;

        public DataWrapper(List<Route> routes, List<SubRoute> subRoutes) {
            this.routes = routes;
            this.subRoutes = subRoutes;
        }
// Getters and setters
    }

}
