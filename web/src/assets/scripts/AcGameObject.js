const AC_GAME_OBJECT = [];
export class AcGameObject{
    constructor(){
        // 将当前实例添加到全局的 AC_GAME_OBJECT 数组中
        AC_GAME_OBJECT.push(this);
        this.timedelta = 0; // 用于记录上一次更新的时间差，用于计算 deltaTime
        this.has_called_start = false; // 用于标记是否已经调用过 start 方法
    }


start(){  //启动游戏，只执行一次
}
update(){ //每一帧执行一次，除了第一帧之外
}
on_destroy(){ //销毁前

}
destroy(){
   this.on_destroy();
   for(let i in AC_GAME_OBJECT){
       if(AC_GAME_OBJECT[i] === this){
           AC_GAME_OBJECT.splice(i,1);
           break;
       }
   }
}
}
let last_timestamp;//用于记录上一次执行的时刻
const step = timedelta => {
    for(let obj of AC_GAME_OBJECT){
        if(!obj.has_called_start){
            obj.has_called_start = true;
            obj.start(); 
        }
        else{
            obj.timedelta = timedelta - last_timestamp; // 计算当前帧与上一帧的时间差;
            obj.update();
        }
    }
    last_timestamp = timedelta;
   requestAnimationFrame(step); 
}

requestAnimationFrame(step);// 启动游戏循环