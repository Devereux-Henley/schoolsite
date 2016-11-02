(ns schoolsite.views 
 (:require [re-frame.core :as re-frame]
           [re-com.core :as re-com]
           [reagent.core :as reagent])) 
(comment
  "Adapt CSSTransitionGroup class in order to animate routing transitions later on.")

(def css-transition-group
  (reagent/adapt-react-class js/React.addons.CSSTransitionGroup))

(comment
  "Panel views")

(defn home-panel
  []
  [re-com/v-box
   :gap "20px"
   :children
   [[:p "Hello this is the home page."]
    [:p "Here I will have meaningless information and images!"] 
    [:p "Whatever"]
    [:img 
     {:id  "portrait"
      :src "../images/IMG_2757.jpg"}]]])
   

(defn about-panel
  []
  [re-com/v-box
   :gap "20px"
   :children
   [[:p "I am currently a Senior in Computer Science at Kansas State University and plan to graduate in May 2017."]
    [:p "From 2014-2015 I worked in Kansas States Office of Mediated Education as Quality Assurance for KSU web applications."]
    [:p "From 2015 to the present I have worked for Kansas States Office of Mediated Education as an Integration Developer."]]])

(defn contact-panel
  []
  [re-com/v-box
   :gap "20px"
   :children 
   [[:p "Email: devereux@ksu.edu"]
    [:div
     [re-com/hyperlink-href 
       :href "https://github.com/Devereux-Henley" 
       :label "Github"
       :class "main-text"]]
    [:div
     [re-com/hyperlink-href 
       :href "https://www.linkedin.com/in/devereux-henley-a84613102" 
       :label "Linkedin"
       :class "main-text"]]]])

(comment
  "Views object. This will be used with the :current-panel subscription to determine our view.")

(defn views
  []
  {:home home-panel
   :about about-panel
   :contact contact-panel})

(comment
  "Components that consume subscriptions.")

(defn nav-button 
  [id label url]
  (fn []
    [re-com/hyperlink-href
     :class "nav-text"
     :style {:text-decoration "none"}
     :label label
     :href url
     :attr {:id id}]))

(defn nav-button-vec
  []
  [[nav-button "home-btn" "Home" "#"] 
   [nav-button "about-btn" "About" "#about"] 
   [nav-button "contact-btn" "Contact" "#contact"]])

(defn nav-bar 
  []
  (fn []
    [:nav.navbar
     [re-com/h-box
      :class "navbar-default main-text"
      :justify :end
      :gap "3vw"
      :children (nav-button-vec)]]))

(defn content-panel 
  [id]
  (let [content (re-frame/subscribe [:current-panel])]
    (fn []
      [:div.content.main-text
       {:id id}
       [css-transition-group
        {:transition-name "pane" 
         :transition-enter-timeout 500
         :transition-leave-timeout 10
         :component "div"}
        ^{:key @content}
        [(@content (views))]]])))

(defn icon-button
  [id icon-name url]
  (fn []
    [re-com/md-icon-button
     :md-icon-name icon-name
     :on-click #(.open js/parent url)
     :size :larger
     :attr {:id id}]))

(defn icon-bar
  []
  (fn []
    [:nav.iconbar
     [re-com/h-box
      :justify :center
      :children [[icon-button "github" "zmdi-github" "https://github.com/Devereux-Henley"]
                 [icon-button "linkedin" "zmdi-linkedin" "https://www.linkedin.com/in/devereux-henley-a84613102"]]]])) 

(comment
  "Top level exported view. This is exported to our core function on load and mounted to the DOM.")

(defn main-panel []
  (fn []
    [re-com/v-box
     :children [[nav-bar] [content-panel "Content"] [icon-bar]]]))  
