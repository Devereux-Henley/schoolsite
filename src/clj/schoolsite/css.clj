(ns schoolsite.css
 (:require [garden.def :refer [defstyles defkeyframes]]
           [garden.stylesheet :refer [at-media]]))

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
  (at-media
    {:min-width "776px"}
    [:.content
     {:padding "3vh 10vw 55vh 3vw"
      :margin "0vh 15vw 10vh 15vw"
      :min-height "70vh"
      :max-height "70vh"
      :border-width border-width
      :border-style border-style
      :border-color hover-color
      :background-image background-image-panel}])
  (at-media
    {:max-width "776px"}
    [:.content
     {:padding "3vh 10vw 35vh 3vw"
      :margin "0vh 0vw 0vh 0vw"
      :min-height "80vh"
      :max-height "80vh"
      :background-image background-image-panel}]
    [:#icons
     {:background "black"}])
  [:body 
   {:background-image background-image}]
  [:ul
   {:list-style-type "none"
    :font-size "16px"}]
  [:div.rc-md-icon-button
   [:&:hover 
    {:color text-color}]]
  [:img#portrait
   {:width "25vh"
    :display "block"
    :margin-left "auto"
    :margin-right "auto"
    :border "4px solid black"}]
  [:p
   {:margin "0"
    :font-size "16px"}]
  [:.main-text 
   {:color text-color
    :text-shadow text-shadow}
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
  [:.navbar-default 
   {:margin "5vh 15vw"
    :background "none"}]
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
