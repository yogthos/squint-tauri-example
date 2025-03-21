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
          [:h1
           "Welcome to Tauri + Preact"]
          [:div {:class "row"}
           [:a {:href "https://vitejs.dev" :target "_blank"}
            [:img {:src "/vite.svg" :class "logo vite" :alt "Vite logo"}]]
           [:a {:href "https://tauri.app" :target "_blank"}
            [:img {:src "/tauri.svg" :class "logo tauri" :alt "Tauri logo"}]]]
          [:p
           "Click on the Tauri, Vite, and Preact logos to learn more."]
          [:form {:class "row"
                  :onSubmit (fn [e]
                              (.preventDefault e)
                              (greet name set-greeting-msg))}
           [:input {:id "greet-input"
                    :onInput (fn [e]
                               (set-name (-> e .-currentTarget .-value)))
                    :placeholder "Enter a name..."}]
           [:button {:type "submit"} "Greet"]]
          [:p greet-msg]]))