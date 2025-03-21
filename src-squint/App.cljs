(ns App
  (:require
   [preact/hooks :as hooks]
   ["@tauri-apps/api/core" :as tauri]))

(defn ^:async greet [name set-greet-msg]
  (set-greet-msg (js-await (tauri/invoke "greet" {:name name}))))

(defn App []
  (let [[greet-msg set-greeting-msg] (hooks/useState "")
        [name set-name] (hooks/useState "")]
    #jsx [:main {:class "container"}
          [:div {:class "p-6"}
           [:h1 {:class "title"}
            "Welcome to Tauri + Preact"]
           [:div {:class "row"}
            [:a {:href "https://vitejs.dev" :target "_blank"}
             [:img {:src "/vite.svg" :class "logo vite" :alt "Vite logo"}]]]
           [:p
            "Click on the Tauri, Vite, and Preact logos to learn more."]
           [:form {:onSubmit (fn [e]
                               (.preventDefault e)
                               (greet name set-greeting-msg))}
            [:div {:class "field"}
             [:input {:class "input"
                      :id "greet-input"
                      :onInput (fn [e]
                                 (set-name (-> e .-currentTarget .-value)))
                      :placeholder "Enter a name..."}]]
            [:button {:class "button"
                      :type "submit"} "Greet"]]
           [:p greet-msg]]]))