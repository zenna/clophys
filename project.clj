(defproject clophys "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-cljsbuild "1.0.2"]]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2173"]
                 [org.clojure/core.match "0.2.1"]
                 [clozen "0.1.0-SNAPSHOT"]
                 [prismatic/dommy "0.1.2"]]
  :source-paths ["src/clj"]
  :cljsbuild {
      :builds [{
          ; The path to the top-level ClojureScript source directory:
          :source-paths ["src/cljs"]
          ; The standard ClojureScript compiler options:
          ; (See the ClojureScript compiler documentation for details.)
          :compiler {
            :output-to "resources/js/main.js"  ; default: target/cljsbuild-main.js
            :optimizations :whitespace
            :pretty-print true}}]})
