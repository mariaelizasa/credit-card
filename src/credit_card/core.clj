(ns credit-card.core
  (:require [credit-card.db :as db]
            [credit-card.logic :as logic]))

(println "\n Added Purchase" (logic/add-purchase logic/purchases (db/all-purchases)))

(println "\n Create client:" (logic/create-client-data "Maria" 123412312312 "maria@gmail.com"))

(println "\n Sum of all prices:" (logic/sum-purchases logic/total-purchases))

(println "\n Total price by Category:" (logic/total-price-by-category logic/total-purchases))

(println "\n Filter by Price or Establishment:" (logic/search-by-price-or-establishment "Burguer King" logic/total-purchases))


