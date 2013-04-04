(ns leiningen.standalone-repl
  (:require [leiningen.trampoline :as trampoline]
            [leiningen.repl :as repl]
            [leiningen.run :as run]
            [leiningen.core.project :as project]))

(defn standalone-repl
  "Backport of a bugfix for trampoline repl."
  [project]
  (trampoline/trampoline (project/merge-profiles project
                                                 [repl/trampoline-profile])
                         "run" "-m" "reply.main/launch-standalone" "nil"))