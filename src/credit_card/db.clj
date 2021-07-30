(ns credit-card.db)

(defn all-orders []
  {{:date       1
    :price       (rand-int 1000)
    :commerce   "Mc Donalds"
    :category "Food"}

   {:date        2
    :price       (rand-int 1000)
    :commerce    "Bolovo"
    :category  "Locker Room"}

   {:date        3
    :price       (rand-int 1000)
    :commerce    "Nike"
    :category  "Locker Room"}

   {:date        824
    :price       (rand-int 1000)
    :commerce    "Burguer King"
    :category  "Food"}

   {:date        63
    :price       (rand-int 1000)
    :commerce    "Riot Games"
    :category  "Leisure"}

   {:date        5
    :price       (rand-int 1000)
    :commerce    "Sushi"
    :category  "Food"}

   {:date        6
    :price       (rand-int 1000)
    :commerce    "Burguer King"
    :category  "Food"}

   {:date        8
    :price       (rand-int 1000)
    :commerce    "Family Park"
    :category  "Leisure"}

   {:date        9
    :price       (rand-int 1000)
    :commerce    "Spoletto"
    :category  "Food"}

   {:date        24
    :price       (rand-int 1000)
    :commerce    "Adidas"
    :category  "Locker Room"}})

