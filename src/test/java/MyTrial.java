import com.tmjee.evolution.mata.ValidationBuilder;
import com.tmjee.evolution.mata.Validators;

public class MyTrial {


    public static void main(String[] args) throws Exception {

        new ValidationBuilder()
                .using(new Object())
                    .withProperty("prop1")
                        .addValidator(Validators.notEmpty())
                        .addValidator(Validators.min(2))
                    .withProperty("prop2")
                        .addValidator(Validators.notEmpty())
                .using(new Object())
                    .withProperty("ppp1")
                        .addValidator(Validators.notEmpty())
                .build()
                .validate();
    }
}
