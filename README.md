# FloodgateCumulus

## Examples

[modal.yml](src%2Fmain%2Fresources%2Fcumulus%2Fmodal.yml)
```yaml
type: modal
title: Simple modal
content: Hello! %player_name%
button1:
  text: OK
  commands: [say %player_name%]
button2:
  text: Cancel
```

[modal.js](src%2Fmain%2Fresources%2Fcumulus%2Fmodal.js)
```js
type = "modal"
title = "Simple modal"
content = "Hello! %player_name%"

button1 = {text: "OK", commands: ["say %player_name%"]}

(button2 = {text: "Cancel"})
    .callback = function(p) {
        p.sendMessage("Cancelled")
    }
```