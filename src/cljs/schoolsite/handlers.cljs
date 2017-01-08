(ns schoolsite.handlers
 (:require [re-frame.core :as re-frame]
           [schoolsite.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
  :set-panel
  (fn [db [_ panel]]
    (assoc db :current-panel panel)))
