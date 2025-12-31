package net.foxtrot.ca3e.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.foxtrot.ca3e.CataclysmAwaits;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        sequencedAssembly(pWriter, "andesite_mechanism_pre_brass", item("ca3e:prepared_andesite_mechanism"),
                item("ca3e:incomplete_andesite_mechanism"),
                List.of(
                        new ResultEntry("ca3e:andesite_mechanism", 0.3),
                        new ResultEntry("ca3e:prepared_andesite_mechanism", 0.5),
                        new ResultEntry("thermal:copper_gear", 0.1),
                        new ResultEntry("thermal:copper_coin", 0.1)
                ),
                List.of(
                        step("create:pressing", List.of(item("ca3e:incomplete_andesite_mechanism")), "ca3e:incomplete_andesite_mechanism"),
                        step("create:pressing", List.of(item("ca3e:incomplete_andesite_mechanism")), "ca3e:incomplete_andesite_mechanism")
                ), 4);

        sequencedAssembly(pWriter, "andesite_mechanism_post_brass", item("ca3e:andesite_sheet"),
                item("ca3e:incomplete_andesite_mechanism"),
                List.of(
                        new ResultEntry("ca3e:andesite_mechanism", 0.8),
                        new ResultEntry("thermal:copper_gear", 0.1),
                        new ResultEntry("thermal:copper_coin", 0.1)
                ),
                List.of(
                        step("create:deploying", List.of(item("ca3e:incomplete_andesite_mechanism"), item("thermal:copper_coin")), "ca3e:incomplete_andesite_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_andesite_mechanism"), item("thermal:copper_gear")), "ca3e:incomplete_andesite_mechanism")
                ), 3);

        sequencedAssembly(pWriter, "pneumatic_mechanism", item("create:copper_sheet"),
                item("ca3e:incomplete_pneumatic_mechanism"),
                List.of(
                        new ResultEntry("ca3e:pneumatic_mechanism", 0.85),
                        new ResultEntry("create_aquatic_ambitions:prismarine_alloy_rod", 0.05),
                        new ResultEntry("create_aquatic_ambitions:prismarine_alloy", 0.05),
                        new ResultEntry("create:copper_sheet", 0.05)
                ),
                List.of(
                        step("create:deploying", List.of(item("ca3e:incomplete_pneumatic_mechanism"), item("create_aquatic_ambitions:prismarine_alloy")), "ca3e:incomplete_pneumatic_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_pneumatic_mechanism"), item("create_aquatic_ambitions:prismarine_alloy_rod")), "ca3e:incomplete_pneumatic_mechanism")
                ), 3);

        sequencedAssembly(pWriter, "industrial_mechanism", item("createaddition:zinc_sheet"),
                item("ca3e:incomplete_industrial_mechanism"),
                List.of(
                        new ResultEntry("ca3e:industrial_mechanism", 0.7),
                        new ResultEntry("createaddition:zinc_sheet", 0.1),
                        new ResultEntry("create:brass_nugget", 0.05),
                        new ResultEntry("thermal:iron_gear", 0.05),
                        new ResultEntry("minecraft:chain", 0.05),
                        new ResultEntry("create:belt_connector", 0.05)
                ),
                List.of(
                        step("create:deploying", List.of(item("ca3e:incomplete_industrial_mechanism"), item("create:brass_nugget")), "ca3e:incomplete_industrial_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_industrial_mechanism"), item("thermal:iron_gear")), "ca3e:incomplete_industrial_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_industrial_mechanism"), item("minecraft:chain")), "ca3e:incomplete_industrial_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_industrial_mechanism"), item("create:belt_connector")), "ca3e:incomplete_industrial_mechanism"),
                        step("create:filling", List.of(item("ca3e:incomplete_industrial_mechanism"), fluid("createaddition:seed_oil", 100)), "ca3e:incomplete_industrial_mechanism")
                ), 3);

        sequencedAssembly(pWriter, "computation_mechanism", item("ca3e:invar_sheet"),
                item("ca3e:incomplete_computation_mechanism"),
                List.of(
                        new ResultEntry("ca3e:computation_mechanism", 0.85),
                        new ResultEntry("ca3e:invar_sheet", 0.04),
                        new ResultEntry("morered:red_alloy_wire", 0.03),
                        new ResultEntry("thermal:rf_coil", 0.03),
                        new ResultEntry("create:transmitter", 0.05)
                ),
                List.of(
                        step("create:deploying", List.of(item("ca3e:incomplete_computation_mechanism"), item("morered:red_alloy_wire")), "ca3e:incomplete_computation_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_computation_mechanism"), item("create:transmitter")), "ca3e:incomplete_computation_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_computation_mechanism"), item("thermal:rf_coil")), "ca3e:incomplete_computation_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_computation_mechanism"), item("thermal:gold_coin")), "ca3e:incomplete_computation_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_computation_mechanism"), item("morered:red_alloy_wire")), "ca3e:incomplete_computation_mechanism")
                ), 3);

        sequencedAssembly(pWriter, "advanced_computation_mechanism", item("ca3e:signalum_sheet"),
                item("ca3e:incomplete_advanced_computation_mechanism"),
                List.of(
                        new ResultEntry("ca3e:advanced_computation_mechanism", 0.95),
                        new ResultEntry("ca3e:signalum_sheet", 0.05)
                ),
                List.of(
                        step("create:deploying", List.of(item("ca3e:incomplete_advanced_computation_mechanism"), item("ca3e:computation_mechanism")), "ca3e:incomplete_advanced_computation_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_advanced_computation_mechanism"), item("thermal:netherite_coin")), "ca3e:incomplete_advanced_computation_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_advanced_computation_mechanism"), item("createaddition:capacitor")), "ca3e:incomplete_advanced_computation_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_advanced_computation_mechanism"), item("create:electron_tube")), "ca3e:incomplete_advanced_computation_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_advanced_computation_mechanism"), item("createaddition:electrum_wire")), "ca3e:incomplete_advanced_computation_mechanism")
                ), 2);

        sequencedAssembly(pWriter, "sterile_mechanism", item("ca3e:silver_sheet"),
                item("ca3e:incomplete_sterile_mechanism"),
                List.of(
                        new ResultEntry("ca3e:sterile_mechanism", 0.7),
                        new ResultEntry("ca3e:silver_sheet", 0.1),
                        new ResultEntry("thermal:silver_gear", 0.1),
                        new ResultEntry("thermal:silver_coin", 0.1)
                ),
                List.of(
                        step("create:deploying", List.of(item("ca3e:incomplete_sterile_mechanism"), item("thermal:silver_coin")), "ca3e:incomplete_sterile_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_sterile_mechanism"), item("thermal:silver_gear")), "ca3e:incomplete_sterile_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_sterile_mechanism"), item("minecraft:gold_nugget")), "ca3e:incomplete_sterile_mechanism"),
                        step("create:filling", List.of(item("ca3e:incomplete_sterile_mechanism"), fluid("minecraft:water", 250)), "ca3e:incomplete_sterile_mechanism")
                ), 4);

        sequencedAssembly(pWriter, "heavy_duty_mechanism", item("ca3e:netherite_sheet"),
                item("ca3e:incomplete_heavy_duty_mechanism"),
                List.of(
                        new ResultEntry("ca3e:heavy_duty_mechanism", 0.8),
                        new ResultEntry("ca3e:netherite_sheet", 0.1),
                        new ResultEntry("tconstruct:hepatizon_nugget", 0.05),
                        new ResultEntry("minecraft:chain", 0.05)
                ),
                List.of(
                        step("create:deploying", List.of(item("ca3e:incomplete_heavy_duty_mechanism"), item("thermal:steel_gear")), "ca3e:incomplete_heavy_duty_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_heavy_duty_mechanism"), item("minecraft:chain")), "ca3e:incomplete_heavy_duty_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_heavy_duty_mechanism"), item("thermal:steel_gear")), "ca3e:incomplete_heavy_duty_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_heavy_duty_mechanism"), item("tconstruct:hepatizon_nugget")), "ca3e:incomplete_heavy_duty_mechanism"),
                        step("create:pressing", List.of(item("ca3e:incomplete_heavy_duty_mechanism")), "ca3e:incomplete_heavy_duty_mechanism"),
                        step("create:filling", List.of(item("ca3e:incomplete_heavy_duty_mechanism"), fluid("thermal:light_oil", 20)), "ca3e:incomplete_heavy_duty_mechanism")
                ), 3);

        sequencedAssembly(pWriter, "ultra_lightweight_mechanism", item("ca3e:tin_sheet"),
                item("ca3e:incomplete_ultra_lightweight_mechanism"),
                List.of(
                        new ResultEntry("ca3e:ultra_lightweight_mechanism", 0.7),
                        new ResultEntry("ca3e:tin_sheet", 0.1),
                        new ResultEntry("create:brass_nugget", 0.05),
                        new ResultEntry("thermal:iron_gear", 0.05),
                        new ResultEntry("minecraft:chain", 0.05),
                        new ResultEntry("create:belt_connector", 0.05)
                ),
                List.of(
                        step("create:deploying", List.of(item("ca3e:incomplete_ultra_lightweight_mechanism"), item("create:shaft")), "ca3e:incomplete_ultra_lightweight_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_ultra_lightweight_mechanism"), item("create:cardboard")), "ca3e:incomplete_ultra_lightweight_mechanism"),
                        step("create:deploying", List.of(item("ca3e:incomplete_ultra_lightweight_mechanism"), item("ca3e:tin_sheet")), "ca3e:incomplete_ultra_lightweight_mechanism"),
                        step("create:filling", List.of(item("ca3e:incomplete_ultra_lightweight_mechanism"), fluid("thermal:latex", 10)), "ca3e:incomplete_ultra_lightweight_mechanism")
                ), 3);
    }

    private static void sequencedAssembly(Consumer<FinishedRecipe> writer, String name, JsonObject ingredient, JsonObject transitionalItem,
                                          List<ResultEntry> results, List<AssemblyStep> steps, int loops) {
        writer.accept(new SequencedAssemblyRecipe(new ResourceLocation(CataclysmAwaits.MOD_ID, name), ingredient, transitionalItem, results, steps, loops));
    }

    private static JsonObject item(String itemId) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("item", itemId);
        return jsonObject;
    }

    private static JsonObject fluid(String fluidId, int amount) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("fluid", fluidId);
        jsonObject.addProperty("amount", amount);
        return jsonObject;
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  CataclysmAwaits.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    private record ResultEntry(String item, double chance) {
    }

    private record AssemblyStep(String type, List<JsonObject> ingredients, List<JsonObject> results) {
    }

    private static AssemblyStep step(String type, List<JsonObject> ingredients, String transitionalItem) {
        return new AssemblyStep(type, ingredients, List.of(item(transitionalItem)));
    }

    private static class SequencedAssemblyRecipe implements FinishedRecipe {

        private static final ResourceLocation SEQUENCED_ASSEMBLY = new ResourceLocation("create", "sequenced_assembly");
        private final ResourceLocation id;
        private final JsonObject ingredient;
        private final JsonObject transitionalItem;
        private final List<ResultEntry> results;
        private final List<AssemblyStep> sequence;
        private final int loops;

        private SequencedAssemblyRecipe(ResourceLocation id, JsonObject ingredient, JsonObject transitionalItem, List<ResultEntry> results, List<AssemblyStep> sequence, int loops) {
            this.id = id;
            this.ingredient = ingredient;
            this.transitionalItem = transitionalItem;
            this.results = results;
            this.sequence = sequence;
            this.loops = loops;
        }

        @Override
        public void serializeRecipeData(JsonObject jsonObject) {
            jsonObject.add("ingredient", ingredient);
            jsonObject.add("transitionalItem", transitionalItem);

            JsonArray resultArray = new JsonArray();
            for (ResultEntry result : results) {
                JsonObject resultObject = new JsonObject();
                resultObject.addProperty("item", result.item());
                resultObject.addProperty("chance", result.chance());
                resultArray.add(resultObject);
            }
            jsonObject.add("results", resultArray);

            JsonArray sequenceArray = new JsonArray();
            for (AssemblyStep step : sequence) {
                JsonObject stepObject = new JsonObject();
                stepObject.addProperty("type", step.type());

                JsonArray ingredientsArray = new JsonArray();
                step.ingredients().forEach(ingredientsArray::add);
                stepObject.add("ingredients", ingredientsArray);

                JsonArray resultsArray = new JsonArray();
                step.results().forEach(resultsArray::add);
                stepObject.add("results", resultsArray);

                sequenceArray.add(stepObject);
            }

            jsonObject.add("sequence", sequenceArray);
            jsonObject.addProperty("loops", loops);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return Objects.requireNonNull(ForgeRegistries.RECIPE_SERIALIZERS.getValue(SEQUENCED_ASSEMBLY), "Create sequenced assembly serializer is not registered");
        }

        @Override
        public @Nullable JsonObject serializeAdvancement() {
            return null;
        }

        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
