(ns schoolsite.core
 (:require [devtools.core :as devtools]
           [goog.events :as events]
           [re-frame.core :as re-frame]
           [reagent.core :as reagent]
           [schoolsite.config :as config]
           [schoolsite.events :as se :refer [EventListener]]
           [schoolsite.handlers]
           [schoolsite.routing :refer [init-routes]]
           [schoolsite.subs]
           [schoolsite.utils :refer [by-id]]
           [schoolsite.views :as views])  
 (:import [goog.events EventType]))

(defn dev-setup 
  []
  (when config/debug?
    (println "dev mode")
    (devtools/install!)))

(defn mount-root 
  []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn get-pane
  []
  (by-id "content-pane"))

(defn test-queues
  []
  (se/event-queues [(EventListener. (by-id "home-btn") EventType.CLICK (fn [e] (.log js/console "home")))
                    (EventListener. (by-id "about-btn") EventType.CLICK (fn [e] (.log js/console "about")))
                    (EventListener. (by-id "contact-btn") EventType.CLICK (fn [e] (.log js/console "contact")))]))

(defn ^:export init 
  []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (init-routes)
  (mount-root))
