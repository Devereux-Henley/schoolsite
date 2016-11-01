(ns schoolsite.css
 (:require [garden.def :refer [defstyles defkeyframes]]
           [garden.selectors :as s]))

(def background-color "navy")
(def background-image-panel "url(../images/footer_lodyas.png)")

(def text-color "rgba(18, 255, 169, .97)")
(def text-shadow "0px 0px 15px rgba(34, 255, 169, 1)")

(def border-style "ridge")
(def border-width "3px")

(def hover-color "rgba(82, 196, 138, .97)")

(def nav-defaults {:color hover-color
                   :text-decoration "none"})

(def background-image "url(../images/dark_dotted2.png)")

(defstyles screen 
  [:body 
   {:background-image background-image}]
  [:img#portrait
   {:width "100%"
    :border "4px solid black"}]
  [:p
   {:margin "0"}]
  [:.main-text 
   {:color text-color
    :text-shadow text-shadow
    :font-size "16px"}
   [:&:hover
    nav-defaults
    [:&:visited
     nav-defaults]]
   [:&:active
    nav-defaults]
   [:p
    {:color text-color
     :text-decoration "none"}]]
  [:.nav-text 
   {:color text-color
    :text-shadow text-shadow
    :font-size "20px"} 
   [:&:hover 
    nav-defaults
    [:&:visited
     nav-defaults]]
   [:&:active
    nav-defaults]
   [:&:visited
    {:color text-color
     :text-decoration "none"}]]
  [:.content 
   {:padding "3vh 10vw 55vh 3vw"
    :margin "0vh 20vw 30vh 20vw"
    :max-height "60vh"
    :border-width border-width
    :border-style border-style
    :border-color hover-color
    :background-color "#0a2d24"}] 
  [:.navbar-default 
   {:margin "5vh 15vw"
    :background-image background-image}]
  [:.pane-enter
   {:opacity "0.01"
    :transition-delay ".5s"
    :transition "opacity .5s ease-in"}]
  [:.pane-enter.pane-enter-active
   {:opacity "1"}] 
  [:.pane-leave
   {:opacity "0"
    :transition "opacity 0s ease-in"}]
  [:.pane-leave.pane-leave-active
   {:opacity "0"}]) 
