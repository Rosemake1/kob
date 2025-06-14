import { AcGameObject } from "./AcGameObject";  

export class GameMap extends AcGameObject {
    constructor(ctx, parent) {
    super();
       this.ctx = ctx; 
       this.parent = parent;
       this.L = 0;
       this.row = 13;
       this.col = 13;
    }
    start(){

    }

    update_size(){
        this.L = Math.min(this.parent.clientWidth/this.col, this.parent.clientHeight/this.row) ; // 计算每个格子的大小，取宽度和高度的最小值，除以 13 得到每个格子的大小。
        this.ctx.canvas.width = this.L * this.col;
        this.ctx.canvas.height = this.L * this.row;
    }
    update(){
        this.update_size();
        this.render();
    }

    render(){
        const color_even = "#AAD751" , color_odd = "#A2D149"; // 偶数行的格子颜色
        for(let i = 0; i < this.row; i++){ // 遍历每一行
            for(let j = 0; j < this.col; j++){ // 遍历每一列
                if((i + j) % 2 === 0){ // 如果是偶数行
                    this.ctx.fillStyle = color_even; // 设置填充颜色为 color_even
                }else{ // 如果是奇数行
                    this.ctx.fillStyle = color_odd; // 设置填充颜色为 color_odd
                }
                this.ctx.fillRect(j * this.L, i * this.L, this.L, this.L);
            }
        }
    }
}