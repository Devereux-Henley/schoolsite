(ns schoolsite.routing
 (:require [bidi.bidi :as bidi]
           [goog.events :as events]
           [goog.history.EventType :as EventType]
           [re-frame.core :as re-frame]) 
 (:import goog.History))

(defn routes
  []
  ["" {""         :home
       "/"        :home
       "about"    :about
       "contact"  :contact}])

(defn match-handler
  [x]
  (re-frame/dispatch [:set-panel (:handler (bidi/match-route (routes) (.-token x)))]))

(def history
  (History.))

(defn init-routes
  [] 
  (events/listen history EventType/NAVIGATE match-handler)
  (doto history (.setEnabled true)))


