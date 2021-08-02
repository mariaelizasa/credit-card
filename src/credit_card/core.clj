(ns credit-card.core
  (:require [credit-card.db :as db]
            [credit-card.logic :as logic]))

(println "\n Create client:" (logic/create-client-data "Maria" 123412312312 "maria@gmail.com"))

(println "\n Sum off all prices:" (logic/sum-purchases (db/all-purchases)))

(println "\n Total price by Category:" (logic/total-price-by-category (db/all-purchases)))

(println "\n Filter by Price or Establishment:" (logic/search-by-price-or-establishment "Nike" (db/all-purchases)))


