(ns zaeny.boot-atomizer
  {:boot/export-tasks true}
  (:require [clojure.java.io   :as io]
            [boot.core         :as boot :refer [deftask]]
            [boot.pod          :as pod]
            [boot.util         :as util]))

(deftask atomizer
  "Boot Task to parse atomizer css classNames"
  [o output-to PATH str "output css"
   r find-class PATH str "findClassName from source directory "
   p path-atomizer PATH str "atomizer exec location default global"]

  (let [output-path (or output-to "main.css")
        tmp-dir (boot/tmp-dir!)
        out (io/file tmp-dir output-path)
        find-source (or find-class "src")
        atomizer (or path-atomizer "atomizer")]
    ; call atomizer from shell
    (boot/with-pre-wrap fileset
      (io/make-parents out)
      (util/info "Atomizer process %s" (.getName out))
      (apply util/dosh [atomizer "-o" (.getPath out) "-R" find-source])
      ;commit to fileset
      (-> fileset
          (boot/add-resource tmp-dir)
          boot/commit!))))

;try parse from macro
;{:spec
; :style}
(comment
 (regex-parser)
 (set-style!))
