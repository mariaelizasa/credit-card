(ns credit-card.models
  (:require [schema.core :as s]))

(s/def Purchase {:purchase/establishment s/Str
                 :purchase/category      s/Str
                 :purchase/price         (s/constrained Float pos?)
                 :purchase/date          s/Str
                 :purchase/id            s/Uuid})


(s/def Credit-Card {:credit-card/id       s/Uuid
                    :credit-card/number   (s/constrained s/Int pos-int?)
                    :credit-card/cvv      (s/constrained s/Int pos-int?)
                    :credit-card/validate s/Str
                    :credit-card/limit    (s/constrained Float pos?)
                    :credit-card/purchase [Purchase]
                    })

(s/def Customer {:costumer/id          s/Uuid
                 :costumer/name        s/Str
                 :costumer/cpf         (s/constrained s/Int pos-int?)
                 :costumer/email       s/Str
                 :costumer/credit-card Credit-Card})


