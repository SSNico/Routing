package networkRouting;

public class MySort {
//    public static void main(String[] args) {
//
//        MySort sort = new MySort();
//        int[] arr  = new int[]{3,22,11,5,400,99,20,22,5};
//        sort.sort(arr);
//        for(int i : arr){
//            System.out.print(i+",");
//        }
//    }

    public void sort(int[] targetArr){//small to large

        int temp = 0;
        for(int i = 0;i<targetArr.length;i++){
            for(int j = i;j<targetArr.length;j++){

                if(targetArr[i]<targetArr[j]){

                   //method one
                    temp = targetArr[i];
                    targetArr[i] = targetArr[j];
                    targetArr[j] = temp;

                    //method two
                   // targetArr[i] = targetArr[i] + targetArr[j];
                   // targetArr[j] = targetArr[i] - targetArr[j];
                   // targetArr[i] = targetArr[i] - targetArr[j];
                   }

             }
        }
    }
}