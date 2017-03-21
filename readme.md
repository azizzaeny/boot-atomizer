# Boot-Atomizer
[![Clojars Project](http://clojars.org/zaeny/boot-atomizer/latest-version.svg)](http://clojars.org/zaeny/boot-atomizer)

[Boot](http://boot-clj.com) Task for [Atomizer](http://acss.io) to compile atomic css      
Atomic CSS is a another way to write css, its feel like functional css... feel great with figwheel...    

Snapshot:
```cljs
(defn component []
  [:div {:class "T(10px) P(1.6em) Translate(50%)"])

;where
  ;T-> top
  ;p-> padding

; Compose
(defn component []
  (let [space "P(1.6em)"
        move "T(10px) Translate(50%)"]
    [:div {:class (str space move)}]))

```
See rational at [acss.io/thinking-in-atomic](https://acss.io/thinking-in-atomic.html)

### Contribution and ahead challanges.
1. Feel free to make changes,
2. it's posible to create own atomizer parser using macros or pure clojure, instead relying on external binary...
   and use [noprompt/garden](https://github.com/noprompt/garden) to parse css
3. other options dont output css but send it down and replace innerText `<style></style>`
3. what if write other style such as:

underscore :
```cljs  
[:button {:"t_10px c_#333"} ]
```
dashed style :
```cljs  
[:button {:"t-10px c-#333"} ]
```
js function style:
```cljs  
[:button {:"t(10px) c(#333)"} ]
```
clojure style!:
```cljs  
[:button {:"(t,10px) (c,#333)"} ]
```

What do you think ?

## STEP GUIDE
### 1.Installation
Sadly we still use tools from outside.. but its ok, lets get a shot...   


1. `npm install -g atomizer`
2. or install modules localy  `npm install atomizer`
3. and add to `path-atomizer` manually. see below.

### 2. pull from clojars and use it in build.boot

Require dependencies :
```clojure
  (set-env! :dependencies '[[zaeny/boot-atomizer "0.1.1"]])

  (require '[zaeny/boot-atomizer :refer [atomizer])
...
```

Set task before cljs or after, because output css :

```clojure
(deftask dev []
  (comp
    ; (serve)
    (watch)
    (atomizer :find-class "src"
              :output-to "main.css"
              :path-atomizer "node_modules/.bin/atomizer")
    ; (reload :on-jsload 'app.core/on-jsload
    ;         :cljs-asset-path ".")
    ; (cljs-repl)
    (cljs :source-map true)))
```

your resources/public/index.html
```html
<link rel="stylesheet" href="main.css">
```

## Options for command-line interfaces
```
  [o output-to PATH str "output css"
   r find-class PATH str "findClassName from source directory "
   p path-atomizer PATH str "atomizer exec location default global"]
```
Example usage from terminal:
`boot atomizer -o main.css -r src/ `
Atomizer will try to parse from src directory
