(ns main
  (:require
   ["preact" :as preact]
   [App :as App]))

(preact/render #jsx [App/App] (js/document.getElementById "root"))
