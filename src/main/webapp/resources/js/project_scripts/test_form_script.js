var timerId;

function calculateMark(answersLength) {
    var counter = 0;
    var inputs = document.getElementsByTagName('input');

    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].checked) {
            if (inputs[i].id === inputs[i].value) {
                counter++;
            }
        }
    }
    var mark = counter / answersLength * 100;
    clearInterval(timerId);
    $("input").prop('disabled', true);
    // document.getElementById("result").innerHTML = "Result: " + mark + " %";
    alert("Your result: " + mark + " %");

    var url = location.href;
    window.location.href = url.substr(0, url.indexOf('?')) + "/finish?" + url.substr(url.indexOf('?') + 1, url.length) + "&mark=" + mark;

}

function initializeTimer(testSeconds) {
    var currentDate = new Date();
    var endDate = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate(), currentDate.getHours(), currentDate.getMinutes(), currentDate.getSeconds() + testSeconds);
    var seconds = (endDate - currentDate) / 1000;
    var minutes = seconds / 60;

    seconds = Math.floor((minutes - Math.floor(minutes)) * 60);
    minutes = Math.floor(minutes);

    setTimePage(minutes, seconds);

    function secOut() {
        if (seconds == 0) {
            if (minutes == 0) {
                endTest(timerId);
            } else {
                minutes--;
                seconds = 59;
            }
        } else {
            seconds--;
        }
        setTimePage(minutes, seconds);
    }

    timerId = setInterval(secOut, 1000)
}

function setTimePage(m, s) {
    var element = document.getElementById("timer");
    element.innerHTML = m + " minutes " + s + " seconds left";
}

function endTest(timerId) {
    alert("Time is over...");
    clearInterval(timerId);
    $("#finish_button").prop('disabled', true);
}