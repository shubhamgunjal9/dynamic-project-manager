var express = require("express");
var cors = require("cors");
var app = express();
var bodyparser = require("body-parser");
app.use(bodyparser.urlencoded({ extended: true }));
app.use(cors());
var mysql = require("mysql");
app.use(express.json());

var conn = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "",
    database: "tplas",
});

var util = require("util");
var execute = util.promisify(conn.query).bind(conn);

app.get("/", function (req, res) {
    res.send("Hello");
});


app.post("/user_login", async function (req, res) {
    var email = req.body.email;
    var password = req.body.password;
    await execute(`SELECT * FROM login WHERE email='${email}' && password='${password}'`, function (err, data) {
        if (data != '') {
            var obj = { "status": 'success', 'token': data[0].user_id };
            res.send(obj);
        }
        else {
            var obj = { "status": 'failed', 'message': 'Invalid Username or Password' };
            res.send(obj);
        }
    });
});

app.post("/add_project", async function (req, res) {
    var d = req.body;
    var sql = `INSERT INTO project (project_theme,project_reason,project_type,project_division,project_category,project_priority,project_department,project_start_date,project_end_date,project_location,project_status) VALUES ('${d.project_theme}','${d.project_reason}','${d.project_type}','${d.project_division}','${d.project_category}','${d.project_priority}','${d.project_department}','${d.project_start_date}','${d.project_end_date}','${d.project_location}','${d.project_status}')`
    await execute(sql, function (err, data) {
        if (data != '') {
            var obj = { "status": 'true', 'message': 'New project added successfully' };
            res.send(obj);
        }
        else {
            var obj = { "status": 'failed', 'message': 'Something went wrong' };
            res.send(obj);
        }
    });
});

app.post("/project_list", async function (req, res) {

    var offset = req.body.offset;
    var pagesize = req.body.pagesize;

    var sql = `SELECT * FROM project ORDER BY project_id DESC LIMIT ${offset}, ${pagesize};`

    await execute(sql, function (err, data) {
        res.send(data);
    });
});

app.get("/project_lists", async function (req, res) {

    var sql = `SELECT * FROM project;`

    await execute(sql, function (err, data) {
        res.send(data);
    });
});

app.post("/search_project_list", async function (req, res) {

    var text =req.body.text;

    var sql = `SELECT * FROM project WHERE CONCAT(project_theme,project_reason,project_type,project_division,project_category,project_priority,project_department,project_start_date,project_end_date,project_location,project_status) LIKE '%${text}%';`

    await execute(sql, function (err, data) {
        res.send(data);
    });
});

app.post("/order_project_list", async function (req, res) {
    var column = req.body.column;
    
    var sql = `SELECT * FROM project ORDER BY ${column} ASC;`
    await execute(sql, function (err, data) {
        res.send(data);
    });
});

app.post("/change_status", async function (req, res) {
    var sql = `UPDATE project SET project_status ='${req.body.status}' WHERE project_id = '${req.body.project_id}'`
    await execute(sql, function (err, data) {
        res.send(data);
    });
});

app.get("/project_count", async function (req, res) {
    var d = new Date();
    var today = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
    var sql = `SELECT
    (SELECT COUNT(*) FROM project) AS count1,
    (SELECT COUNT(*) FROM project WHERE project_status = 'Running') AS count2,
    (SELECT COUNT(*) FROM project WHERE project_status = 'Cancelled') AS count3,
    (SELECT COUNT(*) FROM project WHERE project_status = 'Closed') AS count4,
    (SELECT COUNT(*) FROM project WHERE project_status = 'Running' && project_end_date < '${today}') AS count5
  FROM dual;`
    await execute(sql, function (err, data) {
        var obj = {
            "total_project_count": data[0].count1,
            "running_project_count": data[0].count2,
            "cancelled_project_count": data[0].count3,
            "closed_project_count": data[0].count4,
            "closure_delay_count": data[0].count5
        };
        res.send(obj);
    });
});

app.get("/chart_count", async function (req, res) {
    var sql = `SELECT
    (SELECT COUNT(*) FROM project WHERE project_department = 'Strategy') AS count1,
    (SELECT COUNT(*) FROM project WHERE project_department = 'Finance') AS count2,
    (SELECT COUNT(*) FROM project WHERE project_department = 'Quality') AS count3,
    (SELECT COUNT(*) FROM project WHERE project_department = 'Maintenance') AS count4,
    (SELECT COUNT(*) FROM project WHERE project_department = 'Stores') AS count5,
    (SELECT COUNT(*) FROM project WHERE project_department = 'HR') AS count6,
    (SELECT COUNT(*) FROM project WHERE project_department = 'Strategy' && project_status = 'Closed') AS count7,
    (SELECT COUNT(*) FROM project WHERE project_department = 'Finance' && project_status = 'Closed') AS count8,
    (SELECT COUNT(*) FROM project WHERE project_department = 'Quality' && project_status = 'Closed') AS count9,
    (SELECT COUNT(*) FROM project WHERE project_department = 'Maintenance' && project_status = 'Closed') AS count10,
    (SELECT COUNT(*) FROM project WHERE project_department = 'Stores' && project_status = 'Closed') AS count11,
    (SELECT COUNT(*) FROM project WHERE project_department = 'HR' && project_status = 'Closed') AS count12
  FROM dual;`
    await execute(sql, function (err, data) {
        var obj = [
            [
                data[0].count1,
                data[0].count2,
                data[0].count3,
                data[0].count4,
                data[0].count5,
                data[0].count6,
            ],
            [
                data[0].count7,
                data[0].count8,
                data[0].count9,
                data[0].count10,
                data[0].count11,
                data[0].count12,
            ]
        ];
        res.send(obj);
    });
});

app.listen(1000);