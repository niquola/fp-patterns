(ns fp-patterns.middleware)

;; wrapper function is heigh-oreder function
;; wich take wrapped function & optionaly config
;; and return wrapping lambda (which can shortcat chain)

(defn wrap-one [handler]
  (fn [params]
    (let
      [res (handler
             (conj params :one-down))]
      (conj res :one-up))))

(defn wrap-two [handler]
  (fn [params]
    (let
      [res (handler (conj params :two-down))]
      (conj res :two-up))))

;; here we construct the stack

(defn handler [x] (conj x :handler))

(def mstack (-> handler wrap-one wrap-two))

(println (mstack []))

;; this pattern used in ring middlware
;; more applications?
