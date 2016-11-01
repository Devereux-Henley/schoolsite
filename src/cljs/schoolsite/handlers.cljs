(ns schoolsite.handlers
 (:require [re-frame.core :as re-frame]
           [schoolsite.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
  :match-text-input
  (fn [db [_ match]]
    (assoc db :match-input match)))

(re-frame/reg-event-db
  :set-selected-tab
  (fn [db [_ tab]]
    (assoc db :selected-nav-tab tab)))

(re-frame/reg-event-db
  :set-panel
  (fn [db [_ panel]]
    (assoc db :current-panel panel)))
