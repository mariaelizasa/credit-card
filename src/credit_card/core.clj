(ns credit-card.core
  (:require [credit-card.db]))

(refer-clojure :exclude [range iterate format max min])
(use 'java-time)

(def random-cvv-generator (* (rand-int 10) 999))
(def random-card-number-generator (* (rand-int 999) 999))

(defn create-client-data
  [name cpf email]

  {:id cpf
   :data {:name name
          :cpf cpf
          :email email}

   :credit-card {:number        random-card-number-generator
                 :limit         (rand-int 1000)
                 :validate      (format "dd/MM/yyyy" (zoned-date-time 2028 07 30))
                 :cvv           random-cvv-generator
                 :purchases     (credit-card.db/all-purchases)}})

(def client (create-client-data "Maria" 920 "maria@gmail.com"))

(println "\n\n\n Creating Client" client)






