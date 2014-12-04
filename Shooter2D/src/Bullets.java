import java.awt.Graphics;


public class Bullets {
	private BulletA[][] a;
	public static int bulletAnum=1;
	private BulletB[][] b;
	public static int bulletCnum=1;
	private BulletC[][] c;
	
	Bullets(int num){
		a=new BulletA[num][];
		b=new BulletB[num][2];
		c=new BulletC[num][2];
		for(int i=0 ; i<num ; i++){
			a[i]=null;
			b[i]=null;
			c[i]=null;
		}
	}
	
	public boolean fire(String co){
		switch(co){
			case "a":
				if(System.currentTimeMillis()-Main.player.lastFire>BulletA.fireOffset){
					Main.player.lastFire=System.currentTimeMillis();
					for( int i=0 ; i<Main.BulletNum; i++){
						if(a[i]==null){
							a[i]=new BulletA[Bullets.bulletAnum];
							int aWidth=new BulletA(1,1,1,1).width;
							if(Bullets.bulletAnum%2==0){
								int del=0;
								double dis=0.2;
								for(int j=0 ; j<Bullets.bulletAnum ; j+=2){
									a[i][j]=new BulletA(Main.player.x+Main.player.width/2-aWidth/2,Main.player.y,(del*dis+0.1),-1);
									a[i][j+1]=new BulletA(Main.player.x+Main.player.width/2-aWidth/2,Main.player.y,-(del*dis+0.1),-1);
									del++;
								}
							}
							else{
								double dis=0.10;
								a[i][0]=new BulletA(Main.player.x+Main.player.width/2-aWidth/2,Main.player.y,0,-1);
								for(int j=1 ; j<Bullets.bulletAnum ; j+=2){
									a[i][j]=new BulletA(Main.player.x+Main.player.width/2-aWidth/2,Main.player.y,(j*dis+dis),-1);
									a[i][j+1]=new BulletA(Main.player.x+Main.player.width/2-aWidth/2,Main.player.y,(-j*dis-dis),-1);
								}
							}
							return true;
						}
					}
				}
				break;
			case "b":
				if(System.currentTimeMillis()-Main.player.lastFire>BulletB.fireOffset){
					Main.player.lastFire=System.currentTimeMillis();
					for( int i=0 ; i<Main.BulletNum; i++){
						if(b[i]==null){
							b[i]=new BulletB[2];
							b[i][0]=new BulletB(Main.player.x,Main.player.y,0,-1);
							b[i][1]=new BulletB(Main.player.x+Main.player.width-new BulletB(1,1,1,1).width,Main.player.y,0,-1);
							return true;
						}
					}
				}
				break;
			case "c":
				if(System.currentTimeMillis()-Main.player.lastFire>BulletB.fireOffset){
					Main.player.lastFire=System.currentTimeMillis();
					for( int i=0 ; i<Main.BulletNum; i++){
						if(c[i]==null){
							c[i]=new BulletC[Bullets.bulletCnum];
							int cWidth=new BulletC(1,1,1,1).width;
							if(Bullets.bulletCnum%2==0){
								int del=0;
								double dis=0.2;
								for(int j=0 ; j<Bullets.bulletCnum ; j+=2){
									c[i][j]=new BulletC(Main.player.x+Main.player.width/2-cWidth/2,Main.player.y,(del*dis+0.1),-1);
									c[i][j+1]=new BulletC(Main.player.x+Main.player.width/2-cWidth/2,Main.player.y,-(del*dis+0.1),-1);
									del++;
								}
							}
							else{
								double dis=0.10;
								c[i][0]=new BulletC(Main.player.x+Main.player.width/2-cWidth/2,Main.player.y,0,-1);
								for(int j=1 ; j<Bullets.bulletCnum ; j+=2){
									c[i][j]=new BulletC(Main.player.x+Main.player.width/2-cWidth/2,Main.player.y,(j*dis+dis),-1);
									c[i][j+1]=new BulletC(Main.player.x+Main.player.width/2-cWidth/2,Main.player.y,(-j*dis-dis),-1);
								}
							}
							return true;
						}
					}
				}
				break;
				
		}
		return false;
	};
	
	public void draw(Graphics g){
		for(int i=0 ; i<Main.BulletNum ; i++){
			if(a[i]!=null){
				for(int j=0 ; j<a[i].length ; j++){
					if(a[i][j]!=null){
						a[i][j].draw(g);
					}
				}
			}
			if(b[i]!=null){
				for(int j=0 ; j<b[i].length ; j++){
					b[i][j].draw(g);
				}
			}
			if(c[i]!=null){
				for(int j=0 ; j<c[i].length ; j++){
					if(c[i][j]!=null){
						c[i][j].draw(g);
					}
				}
			}
		}
	};
	
	public void move(){
		for(int i=0 ; i<Main.BulletNum ; i++){
			if(a[i]!=null){
				for(int j=0 ; j<a[i].length ; j++){
					if(a[i][j]!=null){
						a[i][j].move();
					}
				}
			}
			if(b[i]!=null){
				for(int j=0 ; j<b[i].length ; j++){
					b[i][j].move();
				}
			}
			if(c[i]!=null){
				for(int j=0 ; j<c[i].length ; j++){
					if(c[i][j]!=null&&!c[i][j].isExploding){
						c[i][j].move();
					}
				}
			}
		}
	}
	
	public void checkBorder(){
		for(int i=0 ; i<Main.BulletNum ; i++){
			if(a[i]!=null){
				for(int j=0 ; j<a[i].length ; j++){
					if(a[i][j]!=null){
						if(a[i][j].checkBorder()){
							a[i][j]=null;
						}
					}
				}
				
			}
			if(b[i]!=null){
				for(int j=0 ; j<b[i].length ; j++){
					if(b[i][j].checkBorder()){
						b[i]=null;
						break;
					}
				}
			}
			if(c[i]!=null){
				for(int j=0 ; j<c[i].length ; j++){
					if(c[i][j]!=null){
						if(c[i][j].checkBorder()){
							c[i][j]=null;
						}
					}
				}
				
			}
		}
	};
	
	public void checkCollision(){
		for(int i=0 ; i<Main.BulletNum ; i++){
			if(a[i]!=null){
				for(int j=0 ; j<a[i].length ; j++){
					if(a[i][j]!=null){
						if(a[i][j].checkCollision()){
							a[i][j]=null;
						}
					}
				}
			}
			if(b[i]!=null){
				for(int j=0 ; j<b[i].length ; j++){
					b[i][j].checkCollision();
				}
			}
			if(c[i]!=null){
				for(int j=0 ; j<c[i].length ; j++){
					if(c[i][j]!=null){
						if(c[i][j].checkCollision()){
							//c[i][j]=null;
						}
					}
				}
			}
		}
	};
	
	public int getNum(){
		int num=0;
		for(int i=0 ; i<Main.BulletNum ; i++){
			if(a[i]!=null){
				for(int j=0 ; j<a[i].length ; j++){
					if(a[i][j]!=null){
						num++;
					}
				}
			}
			if(b[i]!=null){
				for(int j=0 ; j<b[i].length ; j++){
					if(b[i][j]!=null){
						num++;
					}
				}
			}
			if(c[i]!=null){
				for(int j=0 ; j<c[i].length ; j++){
					if(c[i][j]!=null){
						num++;
					}
				}
			}
		}
		return num;
	}
	
	public int getNumPart(){
		int num=0;
		for(int i=0 ; i<Main.BulletNum ; i++){
			if(c[i]!=null){
				for(int j=0 ; j<c[i].length ; j++){
					if(c[i][j]!=null&&c[i][j].particles!=null){
						for(int k=0 ; k<c[i][j].particles.length ; k++){
							if(c[i][j].particles[k]!=null){
								num++;
							}
						}
					}
				}
			}
		}
		return num;
	}

	public void checkAll() {
		for(int i=0 ; i<Main.BulletNum ; i++){
			if(a[i]!=null){
				boolean live=false;
				
				for(int j=0 ; j<a[i].length ; j++){
					if(a[i][j]!=null){
						live=true;
						break;
					}
				}
				if(!live){
					a[i]=null;
				}
			}
			if(c[i]!=null){
				boolean live=false;
				
				for(int j=0 ; j<c[i].length ; j++){
					if(c[i][j]!=null){
						if(c[i][j].isDead){
							c[i][j]=null;
						}
						else{
							live=true;
							break;
						}
					}
				}
				if(!live){
					c[i]=null;
				}
			}
		}
		
	}
}
