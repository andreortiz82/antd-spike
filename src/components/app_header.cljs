(ns components.app-header
  (:require [rum.core :as rum]
            [clojure.string :as str]
            [cljsjs.moment]
            [ant-spike.utils :as common]
            [antizer.rum :as ant]))

(defn logo []
  [:div "StudyTeam"])

(defn avatar
  []
  (ant/avatar {:style {:color "#f56a00" :background-color "#fde3cf"} :class "va-middle"} "CO"))

(rum/defcs auto-complete < (rum/local [] ::data)
  [state]
  (let [data (::data state)]
    [:div
      (ant/auto-complete
        ;; we need to use dataSource instead of data-source, see README.MD
        {:style {:width "100%"} :dataSource @data
          :on-search
            (fn [x]
              (reset! data
                (if (empty? x)
                  []
                  (take 3 (iterate #(str % (str/reverse %)) x)))))
          :placeholder "Enter something"})]))

(defn nav-menu []
  (ant/menu {:mode "horizontal" :theme :dark}
    (ant/menu-item {:key 1} "Default")
    (ant/menu-item {:key 2} "Default")
    (ant/menu-item {:key 3} "Default")
    (ant/menu-item {:key 4} "Default")))

(rum/defc site-app-header
  []
  [:div
    (ant/layout-header
      (logo)
      (nav-menu)
      (auto-complete)
      (avatar))])
