var express = require("express");
var app = express();
var request = require("request");
var bodyParser = require("body-parser");
var firebase = require("firebase/app");
require("firebase/firestore");

var port = 3000;

var firebaseConfig = {
   //API DATA GO HERE
};
firebase.initializeApp(firebaseConfig);

const admin = require('firebase-admin');

let serviceAccount = require(/*API DATA GOES HERE*/);

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

let db = admin.firestore();

var fact = `USELESS FACT HERE`;

setInterval(()=>{
    request("https://uselessfacts.jsph.pl/random.json?language=en",(error, response, body)=>{
        if(!error && response.statusCode==200){
            var data = JSON.parse(body);
            fact = data.text;
        }
        console.log(fact);
        let fRef = db.collection('random').doc('newest');
        let setAda = fRef.set({
        fact: fact
        }).then(console.log("Fact Updated"));
    });
}, 60000);

app.use(bodyParser.urlencoded({extended: true}));
app.use(express.static("public"));
app.set("view engine","ejs");

app.get("/", (req,res)=>{
    request("https://uselessfacts.jsph.pl/random.json?language=en",(error, response, body)=>{
        if(!error && response.statusCode==200){
            var data = JSON.parse(body);
            fact = data.text;
        }
        res.render("home.ejs",{fact: fact});
    })
});

app.post("/user",(req,res)=>{
    console.log(req.body);
    request("https://uselessfacts.jsph.pl/random.json?language=en",(error, response, body)=>{
        if(!error && response.statusCode==200){
            var data = JSON.parse(body);
            fact = data.text;
        }
        let addUsr = db.collection('ohgod').add({
            name: req.body.name,
            phone: parseInt(req.body.phno, 10),
            message: fact,
            interval: 1
          }).then(ref => {
            console.log('Added document with ID: ', ref.id);
            console.log(db.collection('ohgod').doc(ref.id).get());
          });
    })
    console.log("Done!");
    res.redirect("/#");
});

app.post("/interval",(req,res)=>{
    res.send("SET INTERVAL");
});

app.get("*", (req,res)=>{
    res.redirect('/');
});

app.listen(process.env.PORT||port,process.env.IP,()=>{
    console.log("Server started on port "+port);
});