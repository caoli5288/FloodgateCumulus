type = "FORM"
title = "Simple form"
content = "Line 1\nLine 2\nLine 3"

button({text: "Button 1", commands: ["say hello, %player_name%"]})
button({text: "Button 2"})
    .callback = function(p) {
        p.sendMessage("Button 2 clicked")
    }