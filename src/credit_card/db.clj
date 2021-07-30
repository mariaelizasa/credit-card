(ns credit-card.db)

(refer-clojure :exclude [range iterate format max min])
(use 'java-time)


(defn all-purchases []
[{:date       (format "dd/MM/yyyy" (zoned-date-time 2021 05 30))
  :price       20.58
  :establishment  "Mc Donalds"
  :category "Food"},

 {:date        (format "dd/MM/yyyy" (zoned-date-time 2021 05 16))
  :price       110.30
  :establishment   "Bolovo"
  :category  "Locker Room"},

 {:date        (format "dd/MM/yyyy" (zoned-date-time 2021 06 28))
  :price       540.54
  :establishment   "Nike"
  :category  "Locker Room"},

 {:date        (format "dd/MM/yyyy" (zoned-date-time 2021 04 23))
  :price      63.45
  :establishment   "Burguer King"
  :category  "Food"},

 {:date        (format "dd/MM/yyyy" (zoned-date-time 2021 02 06))
  :price       100.50
  :establishment   "Riot Games"
  :category  "Leisure"},

 {:date        (format "dd/MM/yyyy" (zoned-date-time 2021 01 12))
  :price       75.60
  :establishment   "Sushi"
  :category  "Food"},

 {:date        (format "dd/MM/yyyy" (zoned-date-time 2021 07 28))
  :price       20.40
  :establishment   "Burguer King"
  :category  "Food"},

 {:date        (format "dd/MM/yyyy" (zoned-date-time 2021 12 30))
  :price       200.30
  :establishment   "Family Park"
  :category  "Leisure"},

 {:date        (format "dd/MM/yyyy" (zoned-date-time 2021 11 22))
  :price      45.60
  :establishment   "Spoletto"
  :category  "Food"},

 {:date        (format "dd/MM/yyyy" (zoned-date-time 2021 12 24))
  :price      480.90
  :establishment   "Adidas"
  :category  "Locker Room"}])

