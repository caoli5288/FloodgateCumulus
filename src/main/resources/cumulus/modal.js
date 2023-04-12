type = "MODAL"
title = "Simple modal"
content = "Hello"

button1 = {text: "OK", commands: ["say %player_name%"]}

(button2 = {text: "Cancel"})
    .callback = function(p) {
        p.sendMessage("Cancelled")
    }