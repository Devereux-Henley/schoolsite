(ns schoolsite.events
 (:require [goog.events :as events]))

(defrecord EventListener
  [event-source event-type event-fn])

(defn event-queues
  [event-listeners]
  (doseq [{:keys [event-source event-type event-fn]} event-listeners]
    (events/listen event-source event-type event-fn)))
