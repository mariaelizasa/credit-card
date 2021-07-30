(ns credit-card.core
  (:require [credit-card.db]))


(def random-cvv-generator (* (rand-int 10) 100))
(def random-card-number-generator (* (rand-int 1000) 1000))

for
(defn create-client-data
  [name cpf email]

  {:id cpf
   :data {:name name
          :cpf cpf
          :email email}

   :credit-card {:number random-card-number-generator
                 :initial-limit 400
                 :validate 10
                 :cvv random-cvv-generator}})

(def client (create-client-data "Maria" 920 "maria@gmail.com"))


