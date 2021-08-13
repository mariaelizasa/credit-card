(ns credit-card.models
  (:require [schema.core :as s]))

(s/def Purchase {:purchase/establishment s/Str
                 :purchase/category      s/Str
                 :purchase/price         (s/constrained Float pos?)
                 :purchase/date          s/Str
                 :purchase/id            s/Uuid})

(s/def Client {:name  s/Str
               :cpf   (s/constrained s/Int pos-int?)
               :email s/Str})


