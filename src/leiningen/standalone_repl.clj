(ns leiningen.standalone-repl
  (:require [leiningen.trampoline :as trampoline]
            [leiningen.repl :as repl]
            [leiningen.core.eval :as eval]
            [leiningen.core.project :as project]
            [leiningen.core.user :as user]))

(defn- trampoline-repl [project]
  (let [profiles [(:repl (user/profiles) repl/profile) repl/trampoline-profile]]
    (eval/eval-in-project
     (project/merge-profiles project profiles)
     `(reply.main/launch-standalone {})
     `(require ~@(#'leiningen.repl/init-requires project 'reply.main)))))

(defn standalone-repl
  "Backport of a bugfix for trampoline repl."
  [project]
  (with-redefs [leiningen.repl/trampoline-repl trampoline-repl]
    (trampoline/trampoline project "repl")))