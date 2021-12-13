const video5 = document.getElementsByClassName('input_video5')[0];
const out5 = document.getElementsByClassName('output5')[0];
const controlsElement5 = document.getElementsByClassName('control5')[0];
const canvasCtx5 = out5.getContext('2d');

const fpsControl = new FPS();

const spinner = document.querySelector('.loading');

let user = new Set(); //user informaiton
var userName;
//var wherei;

var getData;
var arrXY = [];
spinner.ontransitionend = () => {
  spinner.style.display = 'none';
};



function syncDelay(milliseconds){
 var start = new Date().getTime();
 var end=0;
 while( (end-start) < milliseconds){
     end = new Date().getTime();
 }
}




function zColor(data) {
  const z = clamp(data.from.z + 0.5, 0, 1);
  return `rgba(0, ${255 * z}, ${255 * (1 - z)}, 1)`;
}

function onResultsPose(results) {
  document.body.classList.add('loaded');
  fpsControl.tick();

  canvasCtx5.save();
  canvasCtx5.clearRect(0, 0, out5.width, out5.height);
  canvasCtx5.drawImage(
      results.image, 0, 0, out5.width, out5.height);
  drawConnectors(
      canvasCtx5, results.poseLandmarks, POSE_CONNECTIONS, {
        color: (data) => {
          const x0 = out5.width * data.from.x;
          const y0 = out5.height * data.from.y;
          const x1 = out5.width * data.to.x;
          const y1 = out5.height * data.to.y;

          const z0 = clamp(data.from.z + 0.5, 0, 1);
          const z1 = clamp(data.to.z + 0.5, 0, 1);

          // console.log("=================== x0");
          // console.log(x0);

          
          // console.log("=================== y0");
          // console.log(y0);

          // console.log("=============  x0 + y0");
          // console.log(x0 + y0);
          
          // arrX.push(x1);
          // arrY.push(y1);
          // var arrXY=[];
          // arrXY = arrX+arrY;
          // x = chunk(arrXY, 33);
          // const myX = JSON.stringify(x);
          // 좌표값
          //arrXY.push(x0 + y0); //x값 y값 더한 후 배열에 저장.
          //const jsnXY = JSON.stringify(x); // json 형태로 
          //console.log(x0.length); // 배열 값 출력
	  
	  console.log(typeof(data));
	  //for(var i=0;i<x0.length;i++){ //배열 출력
    	      //console.log(x0[i]);
	  //}	

          //console.log(jsnXY); // json 형태로 출력
          // 




          const gradient = canvasCtx5.createLinearGradient(x0, y0, x1, y1);
          gradient.addColorStop(
              0, `rgba(0, ${255 * z0}, ${255 * (1 - z0)}, 1)`);
          gradient.addColorStop(
              1.0, `rgba(0, ${255 * z1}, ${255 * (1 - z1)}, 1)`);
          return gradient;
        }
      });
  drawLandmarks(
      canvasCtx5,
      Object.values(POSE_LANDMARKS_LEFT)
          .map(index => results.poseLandmarks[index]),
      {color: zColor, fillColor: '#FF0000'});
  drawLandmarks(
      canvasCtx5,
      Object.values(POSE_LANDMARKS_RIGHT)
          .map(index => results.poseLandmarks[index]),
      {color: zColor, fillColor: '#00FF00'});
  drawLandmarks(
      canvasCtx5,
      Object.values(POSE_LANDMARKS_NEUTRAL)
          .map(index => results.poseLandmarks[index]),
      {color: zColor, fillColor: '#AAAAAA'});
  canvasCtx5.restore();
  
  var id = document.getElementById('hello');
  userName = id.innerText;
  
  
  if(userName === null){
  	console.log("PLEASE INSERT YOUR NAME");
  }
  
  else{
  
  // index.html select 바에서 운동의 종류 가져오기
  var kindExercise = document.getElementById("kindExercise");
  kindExercise = kindExercise.options[kindExercise.selectedIndex].value;
  console.log(kindExercise);


  


  // 좌표 데이터를 원하는 값을 추출하기 위함
  var i;
  var tempX = [];
  var tempY = [];
  // tempX 와 Y 에 아래의 15, 16... 번호를 제외하고 값을 담기
  for (i = 11; i < results.poseLandmarks.length; i++) {
    if(i==15 || i==16 || i==17 || i==18 || i==19 || i==20 || i==21 || i==22){
                continue;
    }
    else{
      tempX.push(results.poseLandmarks[i]["x"]*1280);
      tempY.push(results.poseLandmarks[i]["y"]*720);
    }
  }
  arr = tempX.concat(tempY);  // X와 Y 데이터 합치기

  //console.log(arr.length);   







 // 방에 새로 입장한사람의 소켓 아이디 가져오기 
  var count=0;
  if(localStorage.getItem('userData')){		// rtc.js  ->   setItem
    var userData = localStorage.getItem('userData');
    // user 변수는 제일 위에 set으로 선언되어있음 (중복 방지)
    user.add(userData);
  }
  
  
  
  

  mySocketId = localStorage.getItem('mySocketId');   // 소켓 아이디를 rtc.js에서 받아옴
  //console.log(user);
  



  
  
  // 값을 보내는 부분. 나의 소켓 아이디, 데이터, 운동 종류를 보냄.
  try{
    axios.post("https://www.zaba.website/todos", { 
	name: mySocketId+"-valueDiv",
	data: arr,
	kind: kindExercise,
	userName:  userName,
	headers: {'Content-Type': 'application/json'},
    });
  }
  catch(e){
    console.log("서버가 닫혀있거나 전송할 수 없습니다.")
  }




  var userArr = Array.from(user); // 유저 데이터를 set에서 Array로

  syncDelay(500);





  if(arr.length==28){  // 28개의 관절 좌표를 가져왔을 때만 동작.
	// 모든 사람의 딕셔너리 형태로 값을 가져옴.
  	getResultURL = 'https://www.zaba.website/todos/'+"result";  // 요청받을 주소
  	try{
    	  axios({
      	  method : 'get',
      	  url :getResultURL 
    	  }).then((res)=>{
      	  getResult = res;
    	  })
  	}
  	catch(e){
    	  console.log("데이터를 요청받을 수 없습니다.")
  	}

	console.log("MySocketID : " + getResult["data"]["AllData"][mySocketId+"-valueDiv"]);

	var wherei;
	// 상대방이 연결 되었을 때 상대방의 소켓 아이디를 가져옴 
	// 그리고 상대방 소켓 아이디에 맞는 곳에 값을 대입함.
  	try{
  		for(var i=0; i<userArr.length; i++){
  			wherei = userArr[i];
  			var element = document.getElementById(wherei);

			element.innerHTML = getResult['data']['AllData'][wherei];

			if (getResult['data']['AllData'][wherei]=="Stand" || getResult['data']['AllData'][wherei]=="Ready"){
				element.style.backgroundColor="#0026ff";
			}
			else if (getResult['data']['AllData'][wherei]=="Squat"|| getResult['data']['AllData'][wherei]=="Good"){
				element.style.backgroundColor="#09ff00";
			}
			else if (getResult['data']['AllData'][wherei]=="Bent" || getResult['data']['AllData'][wherei]=="Bad"){
				element.style.backgroundColor="#ff0000";
			}
  		}
  	}
  	catch{		// 이상한 소켓 아이디를 가져오면 그부분을 지워버려서 오류 방지 
		console.log(wherei);
		user.delete(wherei);
  	}
  	//console.log(userArr); 
  
  	}
  	else{ // 28 개의 좌표를 가져오지 못 했을 때 
  		console.log("No Estimation");
  	}
  }
}









const pose = new Pose({locateFile: (file) => {
  return `https://cdn.jsdelivr.net/npm/@mediapipe/pose@0.2/${file}`;
}});
pose.onResults(onResultsPose);

const camera = new Camera(video5, {
  onFrame: async () => {

    await pose.send({image: video5});
  },
  width: 1280,
  height: 720
});
camera.start();
// setTimeout(function() {
//   alert('중지?');
//   }, 10000)

new ControlPanel(controlsElement5, {
      // selfieMode: true,
      // upperBodyOnly: false,
      // smoothLandmarks: true,
      // minDetectionConfidence: 0.7,
      // minTrackingConfidence: 0.7

      modelComplexity: 2,
      smoothLandmarks: true,
      enableSegmentation: true,
      smoothSegmentation: true,
      minDetectionConfidence: 0.5,
      minTrackingConfidence: 0.5
    })
    .add([
      new StaticText({title: 'MediaPipe Pose'}),
      fpsControl,
      new Toggle({title: 'Selfie Mode', field: 'selfieMode'}),
      new Toggle({title: 'Upper-body Only', field: 'upperBodyOnly'}),
      new Toggle({title: 'Smooth Landmarks', field: 'smoothLandmarks'}),
      new Slider({
        title: 'Min Detection Confidence',
        field: 'minDetectionConfidence',
        range: [0, 1],
        step: 0.01
      }),
      new Slider({
        title: 'Min Tracking Confidence',
        field: 'minTrackingConfidence',
        range: [0, 1],
        step: 0.01
      }),
    ])
    .on(options => {
      video5.classList.toggle('selfie', options.selfieMode);
      pose.setOptions(options);
    });
