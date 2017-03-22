## Boot-Atomizer
A task for [boot-clj](http://boot-clj.com)    
[![Clojars Project](http://clojars.org/zaeny/boot-atomizer/latest-version.svg)](http://clojars.org/zaeny/boot-atomizer)

Atomic CSS is a another way to write css, Its feels like functional css... feels great with figwheel...    

Snapshot:
```cljs
;Where  ;T-> top  ;p-> padding

(defn component []
  [:div {:class "T(10px) P(1.6em) Translate(50%)"])

; Compose
(defn component []
  (let [space "P(1.6em)"
        move  "T(10px) Translate(50%)"]
    [:div {:class (str space move)}]))

```

See rational at [acss.io/thinking-in-atomic](https://acss.io/thinking-in-atomic.html)
- [Atomic CSS on steroids](https://www.youtube.com/watch?v=988XpUvzslE)
- [What is Atomic CSS? Organizing CSS](https://www.youtube.com/watch?v=NRqbLuKKOlE)    


###  # STEP GUIDE
#### 1.Installation
Sadly we still use tools from outside.. but its ok, lets get a shot...   


1. `npm install -g atomizer`
2. or install modules localy  `npm install atomizer`
3. and add to `path-atomizer` manually. see below.

#### 2. pull from clojars and use it in build.boot

Require dependencies :

```clojure
(set-env! :dependencies '[[zaeny/boot-atomizer "0.1.1"]])

(require '[zaeny/boot-atomizer :refer [atomizer])
```
add task atomizer :
```clojure
(deftask dev []
  (comp (watch) (atomizer))

; robust way
(deftask dev []  
  (comp
   ;(serve)
    (watch)
    (atomizer :find-class "src"
              :output-to "main.css"
              :path-atomizer "node_modules/.bin/atomizer")
  ; (reload :cljs-asset-path ".")
  ; (cljs-repl)
    (cljs :source-map true)))
```

your resources/public/index.html
```html
<link rel="stylesheet" href="main.css">
```

### # Options

_find-class_
not tobe confused by classes things.. it just term used in atomizer
basicly watch and parse text in what directory. to distinct with other boot-cljs compile in `set-env!`
```clojure {:find-class "src-cljs/"} ```

_path-atomizer_
where atomizer bin located  see [https://github.com/acss-io/atomizer](https://github.com/acss-io/atomizer)

### # CLI
```
boot atomizer -o main.css -r src/

Usage: boot atomizer [options]
    Options:

    -o  --output-to     Where to Output CSS [path str]
    -r  --find-class    findClassName or Parse Blob Text from source-directory [path str]
    -p  --path-atomizer atomizer bin  location [default global path]

```

### # Contribution and ahead challanges.
1. feel free to make changes.
2. it's posible to create own atomizer parser using macros or pure clojure, instead relying on external binary...
   and use [noprompt/garden](https://github.com/noprompt/garden) to parse css
3. other options dont output css but send it down and replace innerText `<style></style>`
4. shorthand property for static values `inline block bold center capitalize nowrap` dynamic values such as `z(20) z(a)` for z-index auto keep using ()
5. use macros to combine related  dynamic values `size(20,5/2)` expanded to `w(20) h(5/2)`
6. what if write parser in other style such as:

underscore :
```cljs  
[:button {:class "t_10px c_#333"} ]
```
dashed style :
```cljs  
[:button {:class "t-10px c-#333"} ]
```
js function style:
```cljs  
[:button {:class "t(10px) c(#333)"} ]
```
clojure style!:
```cljs  
[:button {:class "(t,10px) (c,#333)"} ]
```

What do you think ? do you have something ?

or

[Learn form others in this resistences](./learn-from-others.md)
