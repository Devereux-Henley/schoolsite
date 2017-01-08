(ns schoolsite.subs
 (:require-macros [reagent.ratom :refer [reaction]])
 (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub-raw
 :name
 (fn [db]
   (reaction (:name @db))))

(re-frame/reg-sub-raw
  :current-panel
  (fn [db]
    (reaction (:current-panel @db))))
