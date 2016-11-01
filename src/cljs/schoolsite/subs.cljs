(ns schoolsite.subs
 (:require-macros [reagent.ratom :refer [reaction]])
 (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub-raw
 :name
 (fn [db]
   (reaction (:name @db))))

(re-frame/reg-sub-raw
  :match-input
  (fn [db]
    (reaction (:match-input @db))))

(re-frame/reg-sub-raw
  :nav-tabs
  (fn [db]
    (reaction (:nav-tabs @db))))

(re-frame/reg-sub-raw
  :selected-nav-tab
  (fn [db]
    (reaction (:selected-nav-tab @db))))

(re-frame/reg-sub-raw
  :current-panel
  (fn [db]
    (reaction (:current-panel @db))))
