(ns schoolsite.utils)

(defn by-id
  [id]
  (.getElementById js/document id))
  
