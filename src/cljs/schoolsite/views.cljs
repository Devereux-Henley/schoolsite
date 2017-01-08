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

(defn li-gen
  [col]
  (into 
    [:ul]
    (for [list-item col] [:li list-item])))
        

(defn title-wrap
  [text]
  [re-com/title
   :label text
   :level :level2
   :margin-top "0"
   :class "main-text"])

(defn header-wrap
  [text]
  [re-com/title
   :label text
   :margin-top "0"
   :margin-bottom "0"
   :level :level3
   :class "main-text"])

(defn subheadings-wrap
  [titles lists]
  (into
    [:ul]
    (mapv
      (fn [x y] [:li x (li-gen y)])
      titles
      lists)))

(defn home-panel
  []
  [re-com/v-box
   :gap "8px"
   :children
   [[:p "Hello this is the home page."]
    [:img 
     {:id  "portrait"
      :src "images/IMG_2757.jpg"}]]])


(defn about-panel
  []
  [re-com/v-box
   :gap "8px"
   :children
   [[:p "I am currently a Senior in Computer Science at Kansas State University and plan to graduate in May 2017."]
    [:p "Most of what I write in my free time (such as this website) is written in either Clojure or Clojurescript"]]]) 

(defn work-panel
  []
  [re-com/v-box
   :gap "8px"
   :children 
   [(title-wrap
      "General Electric Aviation")
    (header-wrap 
      "Student Software Engineer - 2017-Present:")
    (subheadings-wrap 
      ["Probably doing unit testing."] 
      [["Specifics TBA"]])
    (title-wrap
      "Kansas State University")  
    (header-wrap 
      "Student Integration Developer - 2015-2016:")
    (subheadings-wrap 
      ["Working with the SoftwareAG webMethods stack utilizing:"
       "Prototyping new frontend applications using Javascript & JQuery."
       "Writing tools for data transformation using Python & Clojure."]
      [["Integration Server & Broker"
        "Universal Messaging"
        "Document Publishing and Subscription"
        "Oracle SQL"
        "Web Portlets"]
       []
       []]) 
    (header-wrap "Student Quality Assurance - 2014-2015:")
    (li-gen
     ["Performing application testing for Kansas State web products."
      "Serving as 2nd tier IT support for the University."])]])

(comment
  "Views map. This will be used with the :current-panel subscription to determine our view.")

(defn views
  []
  {:home  home-panel
   :about about-panel
   :work  work-panel})

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
  [[nav-button "home-btn"  "Home"  "#"]
   [nav-button "about-btn" "About" "#about"]
   [nav-button "work-btn"  "Work"  "#work"]])

(defn nav-bar 
  [id]
  (fn []
    [:nav.navbar
     [re-com/h-box
      :attr {:id id}
      :class "navbar-default main-text"
      :justify :end
      :gap "3vw"
      :children (nav-button-vec)]]))

(defn content-panel
  [id]
  (let [content (re-frame/subscribe [:current-panel])]
    (fn []
      [re-com/scroller
       :class "main-text content"
       :v-scroll :auto
       :attr {:id id}
       :child
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
  [id]
  (fn []
    [:nav.iconbar
     [re-com/h-box
      :attr {:id id}
      :justify :center
      :children [[icon-button "github" "zmdi-github" "https://github.com/Devereux-Henley"]
                 [icon-button "linkedin" "zmdi-linkedin" "https://www.linkedin.com/in/devereux-henley-a84613102"]]]])) 

(comment
  "Top level exported view. This is exported to our core function on load and mounted to the DOM.")

(defn main-panel []
  (fn []
    [re-com/v-box
     :children [[nav-bar       "navigation"]
                [content-panel "content"]
                [icon-bar      "icons"]]]))  
