package net.foxtrot.ca3e.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.foxtrot.ca3e.entity.animations.ModAnimationDefinitions;
import net.foxtrot.ca3e.entity.custom.MushletEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.model.geom.builders.*;
import static net.minecraft.client.animation.KeyframeAnimations.animate;


public class MushletModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "mushlet"), "main");
	private final ModelPart mushlet;

	public MushletModel(ModelPart root) {
		this.mushlet = root.getChild("Mushlet");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Mushlet = partdefinition.addOrReplaceChild("Mushlet", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition RLeg = Mushlet.addOrReplaceChild("RLeg", CubeListBuilder.create().texOffs(12, 11).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, -2.0F, -0.5F));

		PartDefinition LLeg = Mushlet.addOrReplaceChild("LLeg", CubeListBuilder.create().texOffs(12, 14).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.25F, -2.0F, -0.5F));

		PartDefinition Body = Mushlet.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 7).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(12, 7).addBox(-1.0F, -3.5F, -1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -2.0F, 0.0F));

		PartDefinition Cap = Mushlet.addOrReplaceChild("Cap", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 12).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -5.5F, -0.5F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(ModAnimationDefinitions.mushletAnimation.MUSHLET_WALKING, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((MushletEntity) entity).idleAnimationState, ModAnimationDefinitions.MUSHLET_IDLE, ageInTicks, 1f);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		mushlet.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return mushlet;
	}
}