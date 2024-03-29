(ns ant-spike.core
    (:require [rum.core :as rum]
              [ant-spike.kitchen :refer [Kitchen]]
              [components.app-header :as app-header]))

(enable-console-print!)

(println "This text is printed from src/ant-spike/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello antd!"}))

(rum/defc home-panel []
  [:div
   (app-header/site-app-header)])
   ; (Kitchen)])

(rum/defc hello-world []
  [:div
   (home-panel)])

(rum/mount (hello-world)
           (. js/document (getElementById "app")))

(defn on-js-reload [])
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
