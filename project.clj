(defproject schoolsite "0.1.0-SNAPSHOT"
 :dependencies [[org.clojure/clojure "1.8.0"]
                [org.clojure/core.async "0.2.395"]
                [org.clojure/clojurescript "1.9.89"]
                [bidi "2.0.13"]
                [reagent "0.6.0-rc" :exclusions [cljsjs/react]]
                [cljsjs/react-with-addons "15.3.1-0"]
                [binaryage/devtools "0.8.0"]
                [re-frame "0.8.0"]
                [re-com "0.8.3"]
                [garden "1.3.2"]
                [ns-tracker "0.3.0"]]

 :plugins [[lein-cljsbuild "1.1.3"]
           [lein-garden "0.2.8"]]

 :min-lein-version "2.5.3"

 :source-paths ["src/clj"]

 :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                   "resources/public/css"]

 :figwheel {:css-dirs ["resources/public/css"]}

 :garden {:builds [{:id           "screen"
                    :source-paths ["src/clj"]
                    :stylesheet   schoolsite.css/screen
                    :compiler     {:output-to     "resources/public/css/screen.css"
                                   :pretty-print? true}}]}

 :profiles
 {:dev
  {:dependencies [[com.cemerick/piggieback "0.2.1"]
                  [org.clojure/tools.nrepl "0.2.11"]]
   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
   :plugins      [[lein-figwheel "0.5.4-7"]]}}    

 :cljsbuild
 {:builds
  [{:id           "dev"
    :source-paths ["src/cljs"]
    :figwheel     {:on-jsload "schoolsite.core/mount-root"}
    :compiler     {:main                 schoolsite.core
                   :output-to            "resources/public/js/compiled/app.js"
                   :output-dir           "resources/public/js/compiled/out"
                   :asset-path           "js/compiled/out"
                   :source-map-timestamp true}}

   {:id           "min"
    :source-paths ["src/cljs"]
    :compiler     {:main            schoolsite.core
                   :output-to       "resources/public/js/compiled/app.js"
                   :optimizations   :advanced
                   :closure-defines {goog.DEBUG false}
                   :pretty-print    false}}]})  
