(ns credit-card.db-datomic
  (:use clojure.pprint)
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/credit-card")

(defn create-and-connect-to-database! []
  (d/create-database db-uri)
  (d/connect db-uri))


(defn delete-database! []
  (d/delete-database db-uri))

(def schema [;Client Schemas
             {:db/ident       :customer/id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/unique      :db.unique/identity}

             {:db/ident       :customer/name
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}

             {:db/ident       :customer/cpf
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one}

             {:db/ident       :customer/email
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}

             {:db/ident       :customer/credit-card
              :db/valueType   :db.type/ref
              :db/cardinality :db.cardinality/one}


             ;Credit card Schema
             {:db/ident       :credit-card/number
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one}

             {:db/ident       :credit-card/cvv
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one}

             {:db/ident       :credit-card/limit
              :db/valueType   :db.type/float
              :db/cardinality :db.cardinality/one}

             {:db/ident       :credit-card/validate
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}

             {:db/ident       :credit-card/purchase
              :db/valueType   :db.type/ref
              :db/cardinality :db.cardinality/many}

             ;Purchase Schema
             {:db/ident       :purchase/id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/unique      :db.unique/identity}

             {:db/ident       :purchase/date
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}

             {:db/ident       :purchase/price
              :db/valueType   :db.type/float
              :db/cardinality :db.cardinality/one}

             {:db/ident       :purchase/establishment
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}

             {:db/ident       :purchase/category
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             ])

(defn create-schema! [conn]
  (d/transact conn schema))

(defn add-purchase! [conn purchase]
  (d/transact conn purchase))

(defn add-credit-card! [conn card]
  (d/transact conn card))

(defn add-customer! [conn customer]
  (d/transact conn customer))
